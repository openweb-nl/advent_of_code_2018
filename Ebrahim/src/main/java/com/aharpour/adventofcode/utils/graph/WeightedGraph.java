package com.aharpour.adventofcode.utils.graph;

public class WeightedGraph extends AbstractGraph {

    public void addRelationShip(String from, String to, int weight) {
        AbstractGraph.Node fromNode = addNode(from);
        AbstractGraph.Node toNode = addNode(to);
        addRelationShip(fromNode, toNode, weight);
        addRelationShip(toNode, fromNode, weight);
    }
}
