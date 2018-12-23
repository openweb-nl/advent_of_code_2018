package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.Pair;
import com.aharpour.adventofcode.utils.graph.AbstractGraph;

import java.util.*;

public class MultiShortestPathCalculator {

    private final AbstractGraph graph;


    public MultiShortestPathCalculator(AbstractGraph graph) {
        this.graph = graph;
    }

    public Integer getPaths(AbstractGraph.Node from, Collection<AbstractGraph.Node> to) {
        Set<AbstractGraph.Node> unvisitedNode = new HashSet<>(graph.getNodes());
        Map<AbstractGraph.Node, Integer> result = new HashMap<>();

        result.put(from, 0);

        Pair<AbstractGraph.Node, Integer> cp = selectNode(unvisitedNode, result, to);
        int min = -1;
        while (cp != null && !isDestinationAndMinIsMore(to, cp, min)) {
            unvisitedNode.remove(cp.getKey());
            for (AbstractGraph.Edge edge : cp.getKey().getTo()) {
                if (unvisitedNode.contains(edge.getTo())) {
                    int weight = edge.getWeight();
                    int currentDis = result.get(edge.getTo()) != null ? result.get(edge.getTo()) : Integer.MAX_VALUE;
                    int newDis = weight + cp.getValue();
                    if (currentDis > newDis) {
                        result.put(edge.getTo(), newDis);
                    }
                }
            }
            if (to.contains(cp.getKey())) {
                min = cp.getValue();
            }
            cp = selectNode(unvisitedNode, result, to);
        }

        HashSet<AbstractGraph.Node> adjusted = new HashSet<>(to);
        adjusted.removeAll(unvisitedNode);
        return min;
    }

    private boolean isDestinationAndMinIsMore(Collection<AbstractGraph.Node> to, Pair<AbstractGraph.Node, Integer> cp, int min) {
        return to.contains(cp.getKey()) && (min >= 0 && min < cp.getValue());
    }

    private List<Path> newPaths(AbstractGraph.Edge edge, List<Path> oldPaths) {
        List<Path> paths = new ArrayList<>(oldPaths.size());
        for (Path oldPath : oldPaths) {
            paths.add(oldPath.deepClone().addSegment(edge));
        }
        return paths;
    }

    private Pair<AbstractGraph.Node, Integer> selectNode(Set<AbstractGraph.Node> unvisitedNode, Map<AbstractGraph.Node, Integer> distanceMap, Collection<AbstractGraph.Node> to) {
        AbstractGraph.Node minNode = null;
        int min = Integer.MAX_VALUE;
        for (AbstractGraph.Node node : unvisitedNode) {
            Integer dis = distanceMap.get(node);
            if (dis != null && dis < min && (!node.isBlocked() || dis == 0 || to.contains(node))) {
                min = dis;
                minNode = node;
            }
        }
        return minNode != null ? new Pair<>(minNode, min) : null;
    }

    private Path pathsToDistance(List<Path> paths) {
        return paths != null && !paths.isEmpty() ? paths.get(0) : null;
    }
}
