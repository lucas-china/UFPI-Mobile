package ufpi.br.ufpimobile.model;

/**
 * Classe relativa as arestas do grafo que forma o mapa onde o Dijkstra sera aplicado
 * Created by Alan R. Andrade on 26/12/2016.
 */

public class Edge {

    private final String id;
    private final Node source;
    private final Node destination;
    private final double weight;

    /**
     *
     * @param id e o identificador daquela aresta
     * @param source e o no daonde esta aresta vem
     * @param destination e o no para onde esta aresta vai
     * @param weight e o peso (distancia) dessa aresta
     */
    public Edge(String id, Node source, Node destination, double weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }
    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
    
}
