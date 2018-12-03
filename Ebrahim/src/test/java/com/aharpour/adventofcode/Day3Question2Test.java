package com.aharpour.adventofcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day3Question2Test {

    private Day3Question2 question = new Day3Question2();

    @Test
    public void givenCases() {
        assertEquals(3, question.calculate("#1 @ 1,3: 4x4\n" +
                "#2 @ 3,1: 4x4\n" +
                "#3 @ 5,5: 2x2"));
    }
}