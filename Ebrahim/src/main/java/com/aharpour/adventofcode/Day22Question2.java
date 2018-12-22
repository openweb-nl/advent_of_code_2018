package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;
import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import com.aharpour.adventofcode.utils.graph.WeightedGraph;
import com.aharpour.adventofcode.utils.graph.utils.Path;
import com.aharpour.adventofcode.utils.graph.utils.ShortestPathCalculator;

/**
 * @author Ebrahim Aharpour
 * @since 12/22/2018
 */
public class Day22Question2 extends Day22 {

    private final WeightedGraph graph = new WeightedGraph();

    public Day22Question2(int depth, IntPair target) {
        super(depth, target, 40);
        for (int x = 0; x < cave.length; x++) {
            for (int y = 0; y < cave[0].length; y++) {
                AbstractGraph.Node[] nodes = addNodes(x, y);
                addEdges(nodes, x, y);
            }
        }
    }

    public int calculate() {
        AbstractGraph.Node origin = graph.getNode(getNodeName(0, 0, 'T'));
        AbstractGraph.Node target = graph.getNode(getNodeName(this.target.getKey(), this.target.getValue(), 'T'));
        Path path = new ShortestPathCalculator(graph).getPath(origin, target);
        return path.getLength();
    }


    private AbstractGraph.Node[] addNodes(int x, int y) {
        char[] gears = gears(cave[x][y]);
        AbstractGraph.Node[] result = new AbstractGraph.Node[gears.length];
        for (int i = 0; i < gears.length; i++) {
            char gear = gears[i];
            result[i] = graph.addNode(getNodeName(x, y, gear));
        }
        for (int i = 0; i < result.length - 1; i++) {
            graph.addBidirectedEdge(result[i], result[i + 1], 7);
        }
        return result;
    }

    private String getNodeName(int x, int y, char gear) {
        return gear + "" + x + "-" + y;
    }

    private void addEdges(AbstractGraph.Node[] nodes, int x, int y) {
        for (AbstractGraph.Node node : nodes) {
            char type = node.getName().charAt(0);
            if (x > 0) {
                String nodeName = getNodeName(x - 1, y, type);
                addRelationShitIfExists(node, nodeName, 1);
            }
            if (y > 0) {
                String nodeName = getNodeName(x, y - 1, type);
                addRelationShitIfExists(node, nodeName, 1);
            }
        }

    }

    private void addRelationShitIfExists(AbstractGraph.Node node, String nodeName, int weight) {
        AbstractGraph.Node o = graph.getNode(nodeName);
        if (o != null) {
            graph.addBidirectedEdge(o, node, weight);
        }
    }

    private char[] gears(int type) {
        switch (type) {
            case 0:
                return new char[]{'T', 'C'};
            case 1:
                return new char[]{'N', 'C'};
            case 2:
                return new char[]{'T', 'N'};
            default:
                throw new IllegalArgumentException();
        }
    }
}