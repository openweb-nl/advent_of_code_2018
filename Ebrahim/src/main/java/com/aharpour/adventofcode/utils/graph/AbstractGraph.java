package com.aharpour.adventofcode.utils.graph;

import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractGraph {

    private Map<String, Node> nodes = new HashMap<>();


    protected void addRelationShip(Node from, Node to, int weight) {
        Edge edge = new Edge(from, to, weight);
        from.to.add(edge);
        to.from.add(edge);
    }

    public Collection<Node> getNodes() {
        return nodes.values();
    }


    public Node getNode(String nodeName) {
        return addNode(nodeName);
    }

    public Node addNode(String nodeName) {
        Node node;
        if (nodes.containsKey(nodeName)) {
            node = nodes.get(nodeName);
        } else {
            node = new Node(nodeName);
            nodes.put(nodeName, node);
        }
        return node;
    }

    @Data
    @AllArgsConstructor
    public static class Edge {
        private Node from;
        private Node to;
        private int weight;
    }

    @ToString(of = "name")
    @EqualsAndHashCode(of = "name")
    public static class Node {
        private final String name;
        @Getter
        @Setter
        private boolean blocked;
        private final Set<Edge> to = new HashSet<>();
        private final Set<Edge> from = new HashSet<>();
        private Map<String, Object> attributes;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Set<Edge> getFrom() {
            return new HashSet<>(from);
        }

        public Set<Edge> getTo() {
            return new HashSet<>(to);
        }

        public Set<Node> getToNodes() {
            return to.stream()
                    .map(Edge::getTo)
                    .collect(Collectors.toSet());
        }

        public Set<Node> getFromNodes() {
            return from.stream()
                    .map(Edge::getFrom)
                    .collect(Collectors.toSet());
        }

        public void addAttribute(String name, Object value) {
            if (attributes == null) {
                attributes = new HashMap<>();
            }
            attributes.put(name, value);
        }

        public <T> T getAttribute(String name) {
            return (T) attributes.get(name);
        }
    }
}
