package com.aharpour.adventofcode;

import org.junit.Test;

import static com.aharpour.adventofcode.Day1Question2.calculate;
import static org.junit.Assert.assertEquals;

public class Day1Question2Test {

    @Test
    public void givenCases() {
        assertEquals(0, calculate("+1\n-1"));
        assertEquals(10, calculate("+3\n\n+3\t+4  -2    -4"));
        assertEquals(5, calculate("-6  +3  +8  +5  -6"));
        assertEquals(14, calculate("+7 +7 -2 -7 -4"));
    }

}