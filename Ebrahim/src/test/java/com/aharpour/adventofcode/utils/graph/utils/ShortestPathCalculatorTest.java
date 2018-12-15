package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import com.aharpour.adventofcode.utils.graph.WeightedGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShortestPathCalculatorTest {

    private WeightedGraph graph = new WeightedGraph();
    private ShortestPathCalculator calculator = new ShortestPathCalculator(graph);

    @Before
    public void init() {
        graph.addRelationShip("A", "B", 1);
        graph.addRelationShip("A", "C", 2);
        graph.addRelationShip("D", "C", 3);
        graph.addRelationShip("B", "D", 5);
        graph.addRelationShip("F", "D", 7);
    }

    @Test
    public void distanceToDTest() {
        Path path = calculator.getPath(graph.getNode("A"), graph.getNode("D"));
        Assert.assertEquals(5, path.getLength());
    }

    @Test
    public void distanceToFTest() {
        Path path = calculator.getPath(graph.getNode("A"), graph.getNode("F"));
        Assert.assertEquals(12, path.getLength());
    }

    @Test
    public void CBlockedTest() {
        AbstractGraph.Node c = graph.getNode("C");
        c.setBlocked(true);

        Path path = calculator.getPath(graph.getNode("A"), graph.getNode("F"));

        Assert.assertEquals(13, path.getLength());
    }

    @Test
    public void DBlockedTest() {
        AbstractGraph.Node d = graph.getNode("D");
        d.setBlocked(true);

        Path path = calculator.getPath(graph.getNode("A"), graph.getNode("F"));

        Assert.assertNull(path);
    }

}