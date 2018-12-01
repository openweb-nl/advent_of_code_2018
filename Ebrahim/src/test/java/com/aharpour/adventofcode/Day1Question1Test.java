package com.aharpour.adventofcode;

import org.junit.Test;

import static com.aharpour.adventofcode.Day1Question1.calculate;
import static org.junit.Assert.assertEquals;

public class Day1Question1Test {

    @Test
    public void givenCases() {
        assertEquals(3, calculate("+1\n+1\n+1"));
        assertEquals(0, calculate("+1\n+1\n-2"));
        assertEquals(-6, calculate("-1\n-2\n-3"));
    }

    @Test
    public void edgeCases() {
        assertEquals(0, calculate(""));
    }
}