package com.aharpour.adventofcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Question2Test {


    @Test
    public void givenCases() {
        assertEquals(9, new Day14Question2().calculate("51589"));
        assertEquals(5, new Day14Question2().calculate("01245"));
        assertEquals(18, new Day14Question2().calculate("92510"));
        assertEquals(2018, new Day14Question2().calculate("59414"));
    }

    @Test
    public void realCases() {
        assertEquals(20179839, new Day14Question2().calculate("824501"));
    }
}