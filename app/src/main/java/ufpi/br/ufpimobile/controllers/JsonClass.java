package ufpi.br.ufpimobile.controllers;

import android.content.Context;
import android.util.Log;

import ufpi.br.ufpimobile.model.Node;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atendimento on 18/12/17.
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
    private void getJSONFromUrl(String theUrl) throws IOException, JSONException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {


            URL url = new URL(theUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.getContent();

            input = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            json = sb.toString();
            Log.i("JRF", json);


            // Transforma a String de resposta em um JSonObject
            jArray = new JSONArray(json);

        } catch (MalformedURLException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
           // input.close();
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    public List<Node> getNodes(String url) throws JSONException, IOException {
        getJSONFromUrl(url);
        List<Node> nodes = null;
        JSONObject Node;
        nodes = new ArrayList<>();
        List<String> neighbors = null;

        for (int i = 0; i < jArray.length(); i++) {
            Node = new JSONObject(jArray.getString(i));
            JSONObject lcl = Node.getJSONObject("localization");
            LatLng localization = new LatLng(lcl.getDouble("latitude"), lcl.getDouble("longitude"));
            JSONArray vizinhos = null;
            vizinhos = Node.getJSONArray("neighbors");
            neighbors = new ArrayList<String>();
            int j;
            for (j = 0; j < vizinhos.length(); j++) {
                if (vizinhos.getString(j) != "") {
                    neighbors.add(vizinhos.getString(j));
                    //Log.v(TAG, "Aresta de " + Node.getString("name") + " para " + vizinhos.getString(j));

                }
                //Log.v(TAG, "Vizinhos de " + Node.getString("name") + ": " + j);
            }


            Node objetonode = new Node(Node.getInt("id"), Node.getString("name"), Node.getString("description"),
                    Node.getInt("type"), Node.getString("services"), localization, Node.getString("email"),
                    Node.getString("website"), Node.getString("phone"), neighbors);
            nodes.add(objetonode);
            neighbors = null;
        }


        return nodes;

    }

}
