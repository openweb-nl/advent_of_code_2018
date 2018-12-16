package com.aharpour.adventofcode.utils.graph;

/**
 * @author Ebrahim Aharpour
 * @since 12/7/2018
 */
public class DirectionalGraph extends AbstractGraph {

    public void addRelationShip(String from, String to) {
        Node fromNode = addNode(from);
        Node toNode = addNode(to);
        addEdge(fromNode, toNode, 1);
    }
}
