package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.Pair;
import com.aharpour.adventofcode.utils.graph.AbstractGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DistanceMapCalculator {

    private final AbstractGraph graph;


    public DistanceMapCalculator(AbstractGraph graph) {
        this.graph = graph;
    }

    public Map<AbstractGraph.Node, Integer> getDistanceMap(AbstractGraph.Node node) {
        Set<AbstractGraph.Node> unvisitedNode = new HashSet<>(graph.getNodes());
        Map<AbstractGraph.Node, Integer> result = new HashMap<>();
        result.put(node, 0);

        Pair<AbstractGraph.Node, Integer> cn = selectNode(unvisitedNode, result);
        while (cn != null) {
            unvisitedNode.remove(cn.getKey());
            for (AbstractGraph.Edge edge : cn.getKey().getTo()) {
                if (unvisitedNode.contains(edge.getTo())) {
                    int weight = edge.getWeight();
                    int currentDis = result.getOrDefault(edge.getTo(), Integer.MAX_VALUE);
                    int newDis = weight + cn.getValue();
                    if (currentDis > newDis) {
                        result.put(edge.getTo(), newDis);
                    }
                }
            }
            cn = selectNode(unvisitedNode, result);
        }
        return result;
    }

    private Pair<AbstractGraph.Node, Integer> selectNode(Set<AbstractGraph.Node> unvisitedNode, Map<AbstractGraph.Node, Integer> distanceMap) {
        AbstractGraph.Node minNode = null;
        int min = Integer.MAX_VALUE;
        for (AbstractGraph.Node node : unvisitedNode) {
            Integer dis = distanceMap.get(node);
            if (dis != null && dis < min && !node.isBlocked()) {
                min = dis;
                minNode = node;
            }
        }
        return minNode != null ? new Pair<>(minNode, min) : null;
    }
}
