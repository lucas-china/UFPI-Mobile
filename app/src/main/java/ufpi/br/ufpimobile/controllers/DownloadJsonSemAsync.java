package ufpi.br.ufpimobile.controllers;

import ufpi.br.ufpimobile.model.Node;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by atendimento on 19/12/17.
 */

public class DownloadJsonSemAsync {
  /*  /**
     * Retorna os nós lidos no json a partir da url enviada
     /
    MainActivity m;

    public void executar(String url){
        List<Node> nodes = doInBackground(url);
        onPostExecute(nodes);
    }
    protected List<Node> doInBackground(String... params) {
        String url = params[0];

        JsonClass jsonClass = new JsonClass();
        try {
            return jsonClass.getNodes(url);
        } catch (JSONException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

    }



    protected void onPostExecute(List<Node> nodes) {
        System.out.print("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOU/n");
        m.processFinish(nodes);
    }

*/
}
