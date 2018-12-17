package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import com.aharpour.adventofcode.utils.graph.WeightedGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MultiShortestPathsCalculatorTest {

    private WeightedGraph graph = new WeightedGraph();
    private MultiShortestPathsCalculator calculator = new MultiShortestPathsCalculator(graph);

    @Before
    public void init() {
        graph.addBidirectedEdge("A", "B", 1);
        graph.addBidirectedEdge("A", "C", 1);
        graph.addBidirectedEdge("A", "D", 1);
        graph.addBidirectedEdge("B", "G", 2);
        graph.addBidirectedEdge("B", "F", 2);
        graph.addBidirectedEdge("C", "G", 2);
        graph.addBidirectedEdge("C", "H", 3);
        graph.addBidirectedEdge("C", "F", 2);
        graph.addBidirectedEdge("D", "F", 2);
        graph.addBidirectedEdge("D", "G", 2);
        graph.addBidirectedEdge("D", "E", 1);
        graph.addBidirectedEdge("F", "E", 1);
        graph.addBidirectedEdge("H", "G", 1);
    }

    @Test
    public void getPaths() {
        AbstractGraph.Node a = graph.getNode("A");
        Set<AbstractGraph.Node> to = new HashSet<>(Arrays.asList(graph.getNode("G"), graph.getNode("H"), graph.getNode("F")));
        Map<AbstractGraph.Node, List<Path>> paths = calculator.getPaths(a, to);
        Assert.assertEquals(2, paths.size());
        Assert.assertEquals(3, paths.get(graph.getNode("G")).size());
        Assert.assertEquals(4, paths.get(graph.getNode("F")).size());
    }
}