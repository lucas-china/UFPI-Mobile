package ufpi.br.ufpimobile.system;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import ufpi.br.ufpimobile.interfaces.InterfaceGetListOfGeopoints;
import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.model.Node;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Método que traça a rota em uma thread em background.
 * Created by zenote on 29/05/2015.
 */
public class AsyncTaskTraceRoute extends AsyncTask<String, Void, String> {

    private GeoPointsDatabase bancoDeDados;
    private InterfaceGetListOfGeopoints interfaceMapFragment;
    private Node origem;
    private Node destino;

    /**
     * Construtor da classe AsyncTaskTraceRoute
     *
     * @param bancoDeDados
     * @param interfaceMapFragment
     */
    public AsyncTaskTraceRoute(GeoPointsDatabase bancoDeDados, InterfaceGetListOfGeopoints interfaceMapFragment) {
        this.bancoDeDados = bancoDeDados;
        this.interfaceMapFragment = interfaceMapFragment;
    }

    /**
     *
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * Método que recebe os pontos inicial e final e devolve uma string contendo os geopoints da rota.
     *
     * @param descricoes Pontos inicial e final
     * @return Json com os geopoints da rota tracada
     */
    @Override
    protected String doInBackground(String... descricoes) {
        String answer = "";

        origem = bancoDeDados.selectByName(descricoes[0]);
        destino = bancoDeDados.selectByName(descricoes[1]);
        if (origem == null && destino == null) {
            return answer;
        }
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin="
                + origem.getLocalization().latitude + "," + origem.getLocalization().longitude + "&destination="
                + destino.getLocalization().latitude + "," + destino.getLocalization().longitude + "&sensor=false";

        HttpResponse response;
        HttpGet request;
        AndroidHttpClient client = AndroidHttpClient.newInstance("route");

        request = new HttpGet(url);
        try {
            response = client.execute(request);
            answer = EntityUtils.toString(response.getEntity());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    /**
     * Método que é executado quando a execução da thread assincrona termina, devolvendo o controle
     * ao fragmento do mapa
     *
     * @param answer Json com os geopoints da rota tracada
     */
    @Override
    protected void onPostExecute(String answer) {
        List<LatLng> list = null;
        if (answer.equals("") == false) {
            try {
                list = buildJSONRoute(answer);
                interfaceMapFragment.devolveListaDeGeoPoints(list, origem, destino);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que separa o json recebido em objetos LatLng para a criação da rota
     *
     * @param json Json contendo todos os geopoints da rota
     * @return Lista com os objetos LatLng retornados no Json
     * @throws JSONException
     */
    public List<LatLng> buildJSONRoute(String json) throws JSONException {
        JSONObject result = new JSONObject(json);
        JSONArray routes = result.getJSONArray("routes");

        //Possivelmente será desejado descobrir a distancia da rota (Para isso, descomente esse método).
        //long distance = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getInt("value");

        JSONArray steps = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        List<LatLng> lines = new ArrayList<LatLng>();
        lines.add(new LatLng(origem.getLocalization().latitude, origem.getLocalization().longitude));//Adicionando o polilyne do inicio

        for (int i = 0; i < steps.length(); i++) {
            String polyline = steps.getJSONObject(i).getJSONObject("polyline").getString("points");
            for (LatLng p : decodePolyline(polyline)) {
                lines.add(p);
            }
        }
        lines.add(new LatLng(destino.getLocalization().latitude, destino.getLocalization().longitude)); //Adicionando o polilyne do fim
        return (lines);
    }

    /**
     * Método que separa cada parte do Json e atribui à sua colocação correta
     *
     * @param encoded
     * @return
     */
    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> listPoints = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));
            listPoints.add(p);
        }
        return listPoints;
    }
}