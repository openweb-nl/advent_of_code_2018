package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import com.aharpour.adventofcode.utils.graph.WeightedGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class DistanceMapCalculatorTest {

    private WeightedGraph graph = new WeightedGraph();
    private DistanceMapCalculator calculator = new DistanceMapCalculator(graph);

    @Before
    public void init() {
        graph.addRelationShip("A", "B", 1);
        graph.addRelationShip("A", "C", 2);
        graph.addRelationShip("D", "C", 3);
        graph.addRelationShip("B", "D", 5);
        graph.addRelationShip("F", "D", 7);
    }

    @Test
    public void distanceMapTest() {
        Map<AbstractGraph.Node, Integer> map = calculator.getDistanceMap(graph.getNode("A"));
        Assert.assertEquals(0, getDistanceWith("A", map));
        Assert.assertEquals(1, getDistanceWith("B", map));
        Assert.assertEquals(2, getDistanceWith("C", map));
        Assert.assertEquals(5, getDistanceWith("D", map));
        Assert.assertEquals(12, getDistanceWith("F", map));


    }

    @Test
    public void CBlockedTest() {
        AbstractGraph.Node c = graph.getNode("C");
        c.setBlocked(true);

        Map<AbstractGraph.Node, Integer> map = calculator.getDistanceMap(graph.getNode("A"));

        Assert.assertEquals(0, getDistanceWith("A", map));
        Assert.assertEquals(1, getDistanceWith("B", map));
        Assert.assertEquals(2, getDistanceWith("C", map));
        Assert.assertEquals(6, getDistanceWith("D", map));
        Assert.assertEquals(13, getDistanceWith("F", map));
    }

    @Test
    public void DBlockedTest() {
        AbstractGraph.Node d = graph.getNode("D");
        d.setBlocked(true);

        Map<AbstractGraph.Node, Integer> map = calculator.getDistanceMap(graph.getNode("A"));

        Assert.assertEquals(0, getDistanceWith("A", map));
        Assert.assertEquals(1, getDistanceWith("B", map));
        Assert.assertEquals(2, getDistanceWith("C", map));
        Assert.assertEquals(5, getDistanceWith("D", map));
        Assert.assertNull(map.get(graph.getNode("F")));
    }

    private int getDistanceWith(String nodeName, Map<AbstractGraph.Node, Integer> map) {
        return map.get(graph.getNode(nodeName));
    }
}