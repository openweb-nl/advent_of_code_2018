package com.aharpour.adventofcode;

import org.junit.Test;

import static com.aharpour.adventofcode.Day2Question1.calculate;
import static org.junit.Assert.assertEquals;

public class Day2Question1Test {

    @Test
    public void givenCases() {
        assertEquals(12, calculate("abcdef\n" +
                "bababc\n" +
                "abbcde\n" +
                "abcccd\n" +
                "aabcdd\n" +
                "abcdee\n" +
                "ababab\n"));
    }

    @Test
    public void edgeCases() {
        assertEquals(0, calculate("abcdef"));
    }
}