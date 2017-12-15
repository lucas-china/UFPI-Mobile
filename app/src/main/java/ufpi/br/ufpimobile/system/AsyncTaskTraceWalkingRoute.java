package ufpi.br.ufpimobile.system;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import ufpi.br.ufpimobile.interfaces.InterfaceGetListOfGeopoints;
import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.model.Graph;
import ufpi.br.ufpimobile.model.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsavel por tracar a rota dada a requisicao do usuario
 * Created by Alan R. Andrade on 16/01/2017.
 */

public class AsyncTaskTraceWalkingRoute extends AsyncTask<String, Void, LinkedList<Node>> {

    private GeoPointsDatabase bancoDeDados;
    private InterfaceGetListOfGeopoints interfaceMapFragment;
    private Node origem;
    private Node destino;
    private Graph grafo;

    /**
     * Construtor da classe AsyncTaskTraceWalkingRoute
     *
     * @param bancoDeDados
     * @param interfaceMapFragment
     * @param grafo
     */
    public AsyncTaskTraceWalkingRoute(GeoPointsDatabase bancoDeDados, InterfaceGetListOfGeopoints interfaceMapFragment, Graph grafo) {
        this.bancoDeDados = bancoDeDados;
        this.interfaceMapFragment = interfaceMapFragment;
        this.grafo = grafo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     * Método que recebe os pontos inicial e final e devolve uma lista contendo os geopoints da rota.
     *
     * @param descricoes Pontos inicial e final
     * @return lista com os geopoints da rota tracada
     */
    @Override
    protected LinkedList<Node> doInBackground(String... descricoes) {
        //String answer = "";


        origem = bancoDeDados.selectByName(descricoes[0]);
        destino = bancoDeDados.selectByName(descricoes[1]);
        if (origem == null && destino == null) {
            return null;
        }

        Dijkstra dijkstra = new Dijkstra(grafo);
        dijkstra.execute(grafo.getNodeByName(origem.getName()));
        LinkedList<Node> path = dijkstra.getPath(grafo.getNodeByName(destino.getName()));

        return path;
    }

    /**
     * Método que é executado quando a execução da thread assincrona termina, devolvendo o controle
     * ao fragmento do mapa
     *
     * @param answer Json com os geopoints da rota tracada
     */
    @Override
    protected void onPostExecute(LinkedList<Node> answer) {
        List<LatLng> list = null;
        if (answer!= null) {
            try {
                list = nodesToGeoPoints(answer);
                interfaceMapFragment.devolveListaDeGeoPoints(list, origem, destino);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Transforma os nos de um caminho em geopoints
     * @param path Caminho como lista ligada de nos
     * @return Retorna uma lista de posicoes de latitude e longitude (LatLng)
     */
    public List<LatLng> nodesToGeoPoints(LinkedList<Node> path){
        List<LatLng> l = new LinkedList<LatLng>();
        int i = 0;
        for(Node n:path){
            l.add(path.get(i).getLocalization());
            i++;
        }
        return l;
    }

}
