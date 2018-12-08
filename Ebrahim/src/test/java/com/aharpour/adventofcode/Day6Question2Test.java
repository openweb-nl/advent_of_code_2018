package com.aharpour.adventofcode;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day6Question2Test {

    private Day6Question2 question = new Day6Question2(32);

    @Test
    public void givenCases() {
        assertEquals(16, question.calculate("1, 1\n" +
                "1, 6\n" +
                "8, 3\n" +
                "3, 4\n" +
                "5, 5\n" +
                "8, 9"));
    }
}