package com.aharpour.adventofcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day16Question1Test {



    @Test
    public void givenCase() {
        Assert.assertEquals(1, new Day16Question1("\n" +
                "\n" +
                "\n" +
                "Before: [3, 2, 1, 1]\n" +
                "9 2 1 2\n" +
                "After:  [3, 2, 2, 1]\n" +
                "\n").calculate());
    }
}