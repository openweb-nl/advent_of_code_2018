package com.aharpour.adventofcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14Question1Test {


    @Test
    public void givenCases() {
        assertEquals("5158916779", new Day14Question1(9).calculate());
        assertEquals("0124515891", new Day14Question1(5).calculate());
        assertEquals("9251071085", new Day14Question1(18).calculate());
        assertEquals("5941429882", new Day14Question1(2018).calculate());
    }

    @Test
    public void realCases() {
        assertEquals("1031816654", new Day14Question1(824501).calculate());
    }

}