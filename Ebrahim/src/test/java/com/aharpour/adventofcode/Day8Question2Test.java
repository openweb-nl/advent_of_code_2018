package com.aharpour.adventofcode;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day8Question2Test {

    private Day8Question2 question = new Day8Question2();

    @Test
    public void givenCases() {
        assertEquals(66, question.calculate("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"));
    }
}