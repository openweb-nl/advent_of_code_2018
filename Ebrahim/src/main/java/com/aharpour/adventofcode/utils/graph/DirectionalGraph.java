package com.aharpour.adventofcode.utils.graph;

import java.util.*;

/**
 * @author Ebrahim Aharpour
 * @since 12/7/2018
 */
public class DirectionalGraph {

    private Map<String, Node> nodes = new HashMap<>();


    public void addRelationShip(String from, String to) {
        Node fromNode = addNode(from);
        Node toNode = addNode(to);
        fromNode.to.add(toNode);
        toNode.from.add(fromNode);
    }

    public Collection<Node> getNodes() {
        return nodes.values();
    }


    public Node getNode(String nodeName) {
        return addNode(nodeName);
    }

    private Node addNode(String nodeName) {
        Node node;
        if (nodes.containsKey(nodeName)) {
            node = nodes.get(nodeName);
        } else {
            node = new Node(nodeName);
            nodes.put(nodeName, node);
        }
        return node;
    }


    public class Node {
        private final String name;
        private final Set<Node> to = new HashSet<>();
        private final Set<Node> from = new HashSet<>();

        private Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Set<Node> getFrom() {
            return new HashSet<>(from);
        }

        public Set<Node> getTo() {
            return new HashSet<>(to);
        }
    }
}
