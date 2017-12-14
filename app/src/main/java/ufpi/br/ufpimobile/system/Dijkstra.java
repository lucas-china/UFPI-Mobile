package ufpi.br.ufpimobile.system;

import ufpi.br.ufpimobile.model.Edge;
import ufpi.br.ufpimobile.model.Graph;
import ufpi.br.ufpimobile.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe que implementa o Dijkstra
 * Created by Alan R. Andrade on 26/12/2016.
 */

public class Dijkstra {

    private final List<Node> nodes;
    private final List<Edge> edges;
    private Set<Node> settledNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Double> distance;

    /**
     * Construtor da classe Dijkstra
     * @param graph grafo em que o algoritmo sera executado
     */
    public Dijkstra(Graph graph) {
        // Create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /**
     * Logica de execucao do Dijkstra
     * @param source Esse no e o ponto inicial do caminho
     */
    public void execute(Node source) {
        settledNodes = new HashSet<Node>();
        unSettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Double>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    /**
     * Acha as distancias minimas entre um no e todos os outros
     * Enche o conjunto unSettledNodes, e os mapas distance e predecessors
     * @param node
     */
    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    /**
     * Acha a distancia direta entre dois nos dados
     * @param node No origem
     * @param target No destino
     * @return Retorna a distancia entre esse nos
     */
    private double getDistance(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
     * Retorna os nos vizinhos a um no dado
     * @param node
     * @return
     */
    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    /**
     * Acha o no de menor distancia ao que esta sendo avaliado no momento
     * @param Nodes
     * @return
     */
    private Node getMinimum(Set<Node> Nodes) {
        Node minimum = null;
        for (Node node : Nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    /**
     * Pergunta se um dado no ja foi pesquisado
     */
    private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }

    /**
     * Calcula a distancia ate o destino.
     * Retorna um valor infinito caso nao haja conexao entre o no sendo avaliado e o destino.
     * @param destination No de destino
     * @return
     */
    private double getShortestDistance(Node destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    /**
     * Retorna um caminho da origem ate o no objetivo e retorna NULL quando este nao existe
     */
    public LinkedList<Node> getPath(Node target) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node step = target;
        // Check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
