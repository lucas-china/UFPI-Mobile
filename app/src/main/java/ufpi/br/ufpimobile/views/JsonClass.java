package ufpi.br.ufpimobile.views;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import ufpi.br.ufpimobile.model.Node;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renato on 14/07/2016.
 */
public class JsonClass {

    private InputStream input = null;

    //JSONObject jObject = null;
    private JSONArray jArray = null;
    private String json = "";
    private static Context mContext = null;

    public static void setContext(Context mContext) {
        JsonClass.mContext = mContext;
    }

    public static Context getContext() {
        return mContext;
    }

    //Recebe sua url
    private void getJSONFromUrl(String url) throws IOException, JSONException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        input = httpEntity.getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        input.close();
        json = sb.toString();
        Log.i("JRF", json);


        // Transforma a String de resposta em um JSonObject
        jArray = new JSONArray(json);


    }

    public List<Node> getNodes(String url) throws JSONException, IOException {
        getJSONFromUrl(url);
        List<Node> nodes = null;
        JSONObject node;
        nodes = new ArrayList<>();
        List<String> neighbors = null;

        for (int i = 0; i < jArray.length(); i++) {
            node = new JSONObject(jArray.getString(i));
            JSONObject lcl = node.getJSONObject("localization");
            LatLng localization = new LatLng(lcl.getDouble("latitude"), lcl.getDouble("longitude"));
            JSONArray vizinhos = null;
            vizinhos = node.getJSONArray("neighbors");
            neighbors = new ArrayList<String>();
            int j;
            for(j = 0; j< vizinhos.length();j++){
                if( vizinhos.getString(j)!= "") {
                    neighbors.add(vizinhos.getString(j));
                    //Log.v(TAG, "Aresta de " + node.getString("name") + " para " + vizinhos.getString(j));

                }
                //Log.v(TAG, "Vizinhos de " + node.getString("name") + ": " + j);
            }




            Node objetoNode = new Node(node.getInt("id"), node.getString("name"), node.getString("description"),
                    node.getInt("type"), node.getString("services"), localization, node.getString("email"),
                    node.getString("website"), node.getString("phone"), neighbors);
            nodes.add(objetoNode);
            neighbors = null;
        }


        return nodes;

    }


}
