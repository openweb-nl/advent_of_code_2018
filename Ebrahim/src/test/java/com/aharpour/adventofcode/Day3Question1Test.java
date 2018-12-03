package com.aharpour.adventofcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day3Question1Test {

    private Day3Question1 question = new Day3Question1();

    @Test
    public void givenCases() {
        assertEquals(4, question.calculate("#1 @ 1,3: 4x4\n" +
                "#2 @ 3,1: 4x4\n" +
                "#3 @ 5,5: 2x2"));
    }
}