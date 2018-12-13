package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day13Question1Test {


    @Test
    public void givenCases() {
        IntPair result = new Day13Question1("/->-\\        \n" +
                "|   |  /----\\\n" +
                "| /-+--+-\\  |\n" +
                "| | |  | v  |\n" +
                "\\-+-/  \\-+--/\n" +
                "  \\------/ ").calculate();
        assertEquals(7, result.getKey());
        assertEquals(3, result.getValue());
    }
}