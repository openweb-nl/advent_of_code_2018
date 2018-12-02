package com.aharpour.adventofcode;

import org.junit.Test;

import static com.aharpour.adventofcode.Day2Question2.calculate;
import static org.junit.Assert.assertEquals;

public class Day2Question2Test {

    @Test
    public void givenCases() {
        assertEquals("fgij", calculate("abcdef\n" +
                "abcde\n" +
                "fghij\n" +
                "klmno\n" +
                "pqrst\n" +
                "fguij\n" +
                "axcye\n"));
    }
}