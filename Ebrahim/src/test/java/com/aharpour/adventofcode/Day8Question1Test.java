package com.aharpour.adventofcode;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day8Question1Test {

    private Day8Question1 question = new Day8Question1();

    @Test
    public void givenCases() {
        assertEquals(138, question.calculate("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2"));
    }
}