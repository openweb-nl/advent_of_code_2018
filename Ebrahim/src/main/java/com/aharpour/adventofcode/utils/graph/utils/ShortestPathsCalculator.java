package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.Pair;
import com.aharpour.adventofcode.utils.graph.AbstractGraph;

import java.util.*;

public class ShortestPathsCalculator {

    private final AbstractGraph graph;


    public ShortestPathsCalculator(AbstractGraph graph) {
        this.graph = graph;
    }

    public List<Path> getPaths(AbstractGraph.Node from, AbstractGraph.Node to) {
        Set<AbstractGraph.Node> unvisitedNode = new HashSet<>(graph.getNodes());
        Map<AbstractGraph.Node, List<Path>> result = new HashMap<>();

        result.put(from, Collections.singletonList(new Path()));

        Pair<AbstractGraph.Node, List<Path>> cp = selectNode(unvisitedNode, result);
        while (cp != null && to != cp.getKey()) {
            unvisitedNode.remove(cp.getKey());
            for (AbstractGraph.Edge edge : cp.getKey().getTo()) {
                if (unvisitedNode.contains(edge.getTo())) {
                    int weight = edge.getWeight();
                    int currentDis = result.get(edge.getTo()) != null ? result.get(edge.getTo()).get(0).getLength() : Integer.MAX_VALUE;
                    List<Path> oldPaths = cp.getValue();
                    int newDis = weight + oldPaths.get(0).getLength();
                    if (currentDis > newDis) {
                        result.put(edge.getTo(), newPaths(edge, oldPaths));
                    } else if (currentDis == newDis) {
                        result.get(edge.getTo()).addAll(newPaths(edge, oldPaths));
                    }
                }
            }
            cp = selectNode(unvisitedNode, result);
        }
        return result.get(to);
    }

    private List<Path> newPaths(AbstractGraph.Edge edge, List<Path> oldPaths) {
        List<Path> paths = new ArrayList<>(oldPaths.size());
        for (Path oldPath : oldPaths) {
            paths.add(oldPath.deepClone().addSegment(edge));
        }
        return paths;
    }

    private Pair<AbstractGraph.Node, List<Path>> selectNode(Set<AbstractGraph.Node> unvisitedNode, Map<AbstractGraph.Node, List<Path>> distanceMap) {
        AbstractGraph.Node minNode = null;
        int min = Integer.MAX_VALUE;
        for (AbstractGraph.Node node : unvisitedNode) {
            Path dis = pathsToDistance(distanceMap.get(node));
            if (dis != null && dis.getLength() < min && !node.isBlocked()) {
                min = dis.getLength();
                minNode = node;
            }
        }
        return minNode != null ? new Pair<>(minNode, distanceMap.get(minNode)) : null;
    }

    private Path pathsToDistance(List<Path> paths) {
        return paths != null && !paths.isEmpty() ? paths.get(0) : null;
    }
}
