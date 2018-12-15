package com.aharpour.adventofcode.utils.graph.utils;

import com.aharpour.adventofcode.utils.IntPair;
import com.aharpour.adventofcode.utils.graph.AbstractGraph;
import com.aharpour.adventofcode.utils.graph.WeightedGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PathsMapCalculatorTest {

    private WeightedGraph graph = new WeightedGraph();
    private PathsMapCalculator calculator = new PathsMapCalculator(graph);

    @Before
    public void init() {
        graph.addRelationShip("A", "B", 1);
        graph.addRelationShip("A", "C", 2);
        graph.addRelationShip("D", "C", 3);
        graph.addRelationShip("B", "C", 1);
        graph.addRelationShip("B", "D", 5);
        graph.addRelationShip("F", "D", 7);
        graph.addRelationShip("F", "A", 12);
    }

    @Test
    public void basicTest() {
        Map<AbstractGraph.Node, List<Path>> map = calculator.getPathsMap(graph.getNode("A"));

        assertEquals(new IntPair(0, 1), getDistanceAndNumberOfPathWith("A", map));
        assertEquals(new IntPair(1, 1), getDistanceAndNumberOfPathWith("B", map));
        assertEquals(new IntPair(2, 2), getDistanceAndNumberOfPathWith("C", map));
        assertEquals(new IntPair(5, 2), getDistanceAndNumberOfPathWith("D", map));
        assertEquals(new IntPair(12, 3), getDistanceAndNumberOfPathWith("F", map));
    }



    @Test
    public void CBlockedTest() {
        AbstractGraph.Node c = graph.getNode("C");
        c.setBlocked(true);

        Map<AbstractGraph.Node, List<Path>> map = calculator.getPathsMap(graph.getNode("A"));

        assertEquals(new IntPair(0, 1), getDistanceAndNumberOfPathWith("A", map));
        assertEquals(new IntPair(1, 1), getDistanceAndNumberOfPathWith("B", map));
        assertEquals(new IntPair(2, 2), getDistanceAndNumberOfPathWith("C", map));
        assertEquals(new IntPair(6, 1), getDistanceAndNumberOfPathWith("D", map));
        assertEquals(new IntPair(12, 1), getDistanceAndNumberOfPathWith("F", map));
    }

    @Test
    public void DBlockedTest() {
        AbstractGraph.Node d = graph.getNode("D");
        d.setBlocked(true);

        Map<AbstractGraph.Node, List<Path>> map = calculator.getPathsMap(graph.getNode("A"));

        assertEquals(new IntPair(0, 1), getDistanceAndNumberOfPathWith("A", map));
        assertEquals(new IntPair(1, 1), getDistanceAndNumberOfPathWith("B", map));
        assertEquals(new IntPair(2, 2), getDistanceAndNumberOfPathWith("C", map));
        assertEquals(new IntPair(5, 2), getDistanceAndNumberOfPathWith("D", map));
        assertEquals(new IntPair(12, 1), getDistanceAndNumberOfPathWith("F", map));
    }

    private IntPair getDistanceAndNumberOfPathWith(String nodeName, Map<AbstractGraph.Node, List<Path>> map) {
        List<Path> paths = map.get(graph.getNode(nodeName));
        return new IntPair(paths.get(0).getLength(), paths.size());
    }

}