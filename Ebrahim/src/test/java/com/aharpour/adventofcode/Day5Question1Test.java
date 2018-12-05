package com.aharpour.adventofcode;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day5Question1Test {

    private Day5Question1 question = new Day5Question1();

    @Test
    public void givenCases() {
        assertEquals(10, question.calculate("dabAcCaCBAcCcaDA"));
    }
}