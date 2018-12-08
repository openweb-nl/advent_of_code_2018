package com.aharpour.adventofcode;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day7Question2Test {

    @Test
    public void givenCases() {
        assertEquals(15, new Day7Question2(0, 2).calculate("Step C must be finished before step A can begin.\n" +
                "Step C must be finished before step F can begin.\n" +
                "Step A must be finished before step B can begin.\n" +
                "Step A must be finished before step D can begin.\n" +
                "Step B must be finished before step E can begin.\n" +
                "Step D must be finished before step E can begin.\n" +
                "Step F must be finished before step E can begin."));
    }
}