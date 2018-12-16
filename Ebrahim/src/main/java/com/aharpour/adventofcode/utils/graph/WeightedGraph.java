package com.aharpour.adventofcode.utils.graph;

public class WeightedGraph extends AbstractGraph {

    public void addBidirectedEdge(String from, String to, int weight) {
        AbstractGraph.Node fromNode = addNode(from);
        AbstractGraph.Node toNode = addNode(to);
        addBidirectedEdge(fromNode, toNode, weight);
    }

    public void addBidirectedEdge(Node fromNode, Node toNode, int weight) {
        addEdge(fromNode, toNode, weight);
        addEdge(toNode, fromNode, weight);
    }

    @Override
    protected void addEdge(Node from, Node to, int weight) {
        boolean alreadyThere = false;
        for (Edge edge : from.getTo()) {
            if (edge.getTo() == to && edge.getWeight() == weight) {
                alreadyThere = true;
            }
        }
        if (!alreadyThere) {
            super.addEdge(from, to, weight);
        }
    }
}
