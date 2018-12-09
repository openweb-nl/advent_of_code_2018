package com.aharpour.adventofcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day9Question1Test {

    private Day9Question1 question = new Day9Question1();

    @Test
    public void givenCases() {
        assertEquals(32, question.calculate("7 players; last marble is worth 25 points"));
        assertEquals(8317, question.calculate("10 players; last marble is worth 1618 points"));
        assertEquals(146373, question.calculate("13 players; last marble is worth 7999 points"));
        assertEquals(2764, question.calculate("17 players; last marble is worth 1104 points"));
        assertEquals(54718, question.calculate("21 players; last marble is worth 6111 points"));
        assertEquals(37305, question.calculate("30 players; last marble is worth 5807 points"));
        assertEquals(412127, question.calculate("418 players; last marble is worth 71339 points"));
        assertEquals(3482394794L, question.calculate("418 players; last marble is worth 7133900 points"));
    }


}