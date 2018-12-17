package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.Pair;
import com.aharpour.adventofcode.utils.graph.AbstractGraph;

import java.util.*;

public class MultiShortestPathsCalculator {

    private final AbstractGraph graph;


    public MultiShortestPathsCalculator(AbstractGraph graph) {
        this.graph = graph;
    }

    public Map<AbstractGraph.Node, List<Path>> getPaths(AbstractGraph.Node from, Collection<AbstractGraph.Node> to) {
        Set<AbstractGraph.Node> unvisitedNode = new HashSet<>(graph.getNodes());
        Map<AbstractGraph.Node, List<Path>> result = new HashMap<>();

        result.put(from, Collections.singletonList(new Path()));

        Pair<AbstractGraph.Node, List<Path>> cp = selectNode(unvisitedNode, result, to);
        int min = -1;
        while (cp != null && !isDestinationAndMinIsMore(to, cp, min)) {
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
            if (to.contains(cp.getKey())) {
                min = cp.getValue().get(0).getLength();
            }
            cp = selectNode(unvisitedNode, result, to);
        }

        HashSet<AbstractGraph.Node> adjusted = new HashSet<>(to);
        adjusted.removeAll(unvisitedNode);
        return toShortestPathToTargetNodes(adjusted, result, min);
    }

    private Map<AbstractGraph.Node, List<Path>> toShortestPathToTargetNodes(Collection<AbstractGraph.Node> to, Map<AbstractGraph.Node, List<Path>> distanceMap, int min) {
        Map<AbstractGraph.Node, List<Path>> r = new HashMap<>();
        for (AbstractGraph.Node node : to) {
            List<Path> paths = distanceMap.get(node);
            if (paths != null && min == paths.get(0).getLength()) {
                r.put(node, paths);
            }
        }
        return r;
    }

    private boolean isDestinationAndMinIsMore(Collection<AbstractGraph.Node> to, Pair<AbstractGraph.Node, List<Path>> cp, int min) {
        return to.contains(cp.getKey()) && (min >= 0 && min < cp.getValue().get(0).getLength());
    }

    private List<Path> newPaths(AbstractGraph.Edge edge, List<Path> oldPaths) {
        List<Path> paths = new ArrayList<>(oldPaths.size());
        for (Path oldPath : oldPaths) {
            paths.add(oldPath.deepClone().addSegment(edge));
        }
        return paths;
    }

    private Pair<AbstractGraph.Node, List<Path>> selectNode(Set<AbstractGraph.Node> unvisitedNode, Map<AbstractGraph.Node, List<Path>> distanceMap, Collection<AbstractGraph.Node> to) {
        AbstractGraph.Node minNode = null;
        int min = Integer.MAX_VALUE;
        for (AbstractGraph.Node node : unvisitedNode) {
            Path dis = pathsToDistance(distanceMap.get(node));
            if (dis != null && dis.getLength() < min && (!node.isBlocked() || dis.getLength() == 0 || to.contains(node))) {
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
