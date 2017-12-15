package ufpi.br.ufpimobile.model;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe relativa ao grafo que representa os pontos cadastrados no mapa
 * Created by Alan R. Andrade on 26/12/2016.
 */

public class Graph {

    private final List<Node> nodes;
    private final List<Edge> edges;

    /**
     *
     * @param nodes e a lista de nos do grafo
     * @param edges e a lista de arestas do grafo
     */
    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;


    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Devolve o no dado o nome dele
     */
    public Node getNodeByName(String name){

        for(Node n: nodes){
            if(name.equals(n.getName()))
                return n;

        }
        return null;
    }

    /**
     * Adiciona uma nova aresta a lista de arestas
     * @param laneId e a identificacao dessa aresta
     * @param noOrigem e o no de origem dessa aresta
     * @param noDestino e o no de destino dessa aresta
     * @param distance e a distancia do ponto de origem ao destino coberta por essa aresta
     */
    public void addEdge(String laneId, String noOrigem, String noDestino,
                        double distance){
        Edge lane = new Edge(laneId, getNodeByName(noOrigem), getNodeByName(noDestino), distance );
        edges.add(lane);
        //Edge lane2 = new Edge(laneId+999, getNodeByName(noDestino), getNodeByName(noOrigem), distance );
        //edges.add(lane2);

    }

    /**
     * Cria o grafo a partir de uma lista de nos
     */
    public void createGraphWithNodes(List<Node> nos){
        int i = 0;

        for(Node n:nos) {
            nodes.add(n);
        }
        for(Node n:nodes) {
            if(n.getNeighbors()!= null) {

                for (int j = 0; j < n.getNeighbors().size(); j++) {
                    addEdge("Edge" + i, n.getName(), n.getNeighbors().get(j), getDistance(n.getName(), n.getNeighbors().get(j)));
                    i++;
                }
            }
        }
    }

    /**
     * Devolve a distancia direta de um no a outro
     * @param origem no de origem
     * @param destino no de destino
     */
    public double getDistance(String origem, String destino){
        float[] results = new float[1];
        LatLng p1, p2;
        p1 = getNodeByName(origem).getLocalization();
        p2 = getNodeByName(destino).getLocalization();
        Location.distanceBetween(p1.latitude, p1.longitude, p2.latitude,
                p2.longitude, results);
        float distance = results[0];
        return distance;
    }

}
