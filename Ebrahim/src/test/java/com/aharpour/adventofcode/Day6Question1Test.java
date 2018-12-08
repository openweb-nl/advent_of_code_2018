package com.aharpour.adventofcode;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Day6Question1Test {

    private Day6Question1 question = new Day6Question1();

    @Test
    public void givenCases() {
        assertEquals(17, question.calculate("1, 1\n" +
                "1, 6\n" +
                "8, 3\n" +
                "3, 4\n" +
                "5, 5\n" +
                "8, 9"));
    }
}