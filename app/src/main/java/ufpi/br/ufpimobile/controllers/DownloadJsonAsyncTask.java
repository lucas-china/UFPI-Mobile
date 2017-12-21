package ufpi.br.ufpimobile.controllers;

import android.os.AsyncTask;
import android.widget.Toast;

import ufpi.br.ufpimobile.model.Node;


import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by atendimento on 18/12/17.
 */

public class DownloadJsonAsyncTask extends AsyncTask<String, Integer, List<Node>> {


    public interface AsyncResponse {
        void processFinish(List<Node> nodes);
    }

    public AsyncResponse delegate = null;


    /**
     * Retorna os nós lidos no json a partir da url enviada
     */
    @Override
    protected List<Node> doInBackground(String... params) {
        String url = params[0];

        JsonClass jsonClass = new JsonClass();
        try {
            return jsonClass.getNodes(url);
        } catch (JSONException e) {
            publishProgress(1);
            return null;
        } catch (IOException e) {
            publishProgress(2);
            return null;
        }

    }


    @Override
    protected void onPostExecute(List<Node> nodes) {
        delegate.processFinish(nodes);
        System.out.print("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOU/n");
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        if (values[0] == 1)
            Toast.makeText(JsonClass.getContext(), "Base de dados corrompida!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(JsonClass.getContext(), "Não foi possível acessar a base de dados!", Toast.LENGTH_SHORT).show();
    }
}
