package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

import org.junit.Assert;
import org.junit.Test;

public class Day22Question1Test {

    @Test
    public void givenCase() {
        Day22Question1 question = new Day22Question1(510, new IntPair(10, 10));
        question.print();
        Assert.assertEquals(114, question.calculate());
    }

    @Test
    public void realCase() {
        Day22Question1 question = new Day22Question1(3198, new IntPair(12,757));
        Assert.assertEquals(9659, question.calculate());

    }


}