package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

import org.junit.Assert;
import org.junit.Test;

public class Day22Question2Test {

    @Test
    public void givenCase() {
        Day22Question2 question = new Day22Question2(510, new IntPair(10, 10));
        Assert.assertEquals(45, question.calculate());
    }

    @Test
    public void realCase() {
        Day22Question2 question = new Day22Question2(3198, new IntPair(12, 757));
        Assert.assertEquals(1043, question.calculate());

    }
}