package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.Pair;
import com.aharpour.adventofcode.utils.graph.AbstractGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShortestPathCalculator {

    private final AbstractGraph graph;


    public ShortestPathCalculator(AbstractGraph graph) {
        this.graph = graph;
    }

    public Path getPath(AbstractGraph.Node from, AbstractGraph.Node to) {
        Set<AbstractGraph.Node> unvisitedNode = new HashSet<>(graph.getNodes());
        Map<AbstractGraph.Node, Path> result = new HashMap<>();

        result.put(from, new Path());

        Pair<AbstractGraph.Node, Path> cp = selectNode(unvisitedNode, result);
        while (cp != null && to != cp.getKey()) {
            unvisitedNode.remove(cp.getKey());
            for (AbstractGraph.Edge edge : cp.getKey().getTo()) {
                if (unvisitedNode.contains(edge.getTo())) {
                    int weight = edge.getWeight();
                    int currentDis = result.get(edge.getTo()) != null ? result.get(edge.getTo()).getLength() : Integer.MAX_VALUE;
                    int newDis = weight + cp.getValue().getLength();
                    if (currentDis > newDis) {
                        result.put(edge.getTo(), cp.getValue().deepClone()
                                .addSegment(edge));
                    }
                }
            }
            cp = selectNode(unvisitedNode, result);
        }
        return result.get(to);
    }

    private Pair<AbstractGraph.Node, Path> selectNode(Set<AbstractGraph.Node> unvisitedNode, Map<AbstractGraph.Node, Path> distanceMap) {
        AbstractGraph.Node minNode = null;
        int min = Integer.MAX_VALUE;
        for (AbstractGraph.Node node : unvisitedNode) {
            Path dis = distanceMap.get(node);
            if (dis != null && dis.getLength() < min && !node.isBlocked()) {
                min = dis.getLength();
                minNode = node;
            }
        }
        return minNode != null ? new Pair<>(minNode, distanceMap.get(minNode)) : null;
    }
}
