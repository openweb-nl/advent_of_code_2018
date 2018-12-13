package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day13Question2Test {

    @Test
    public void givenCases() {
        IntPair result = new Day13Question2("/>-<\\  \n" +
                "|   |  \n" +
                "| /<+-\\\n" +
                "| | | v\n" +
                "\\>+</ |\n" +
                "  |   ^\n" +
                "  \\<->/").calculate();
        assertEquals(6, result.getKey());
        assertEquals(4, result.getValue());
    }
}