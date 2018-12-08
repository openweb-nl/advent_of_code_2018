package com.aharpour.adventofcode;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day5Question2Test {

    private Day5Question2 question = new Day5Question2();

    @Test
    public void givenCases() {
        assertEquals(4, question.calculate("dabAcCaCBAcCcaDA"));
    }
}