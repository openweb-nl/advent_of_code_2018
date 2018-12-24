package com.aharpour.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day19Question2Test {

    @Test
    public void realCase() {
        Assert.assertEquals(18964204, new Day19Question2("#ip 4\n" +
                "addi 4 16 4\n" +
                "seti 1 2 3\n" +
                "seti 1 6 1\n" +
                "mulr 3 1 2\n" +
                "eqrr 2 5 2\n" +
                "addr 2 4 4\n" +
                "addi 4 1 4\n" +
                "addr 3 0 0\n" +
                "addi 1 1 1\n" +
                "gtrr 1 5 2\n" +
                "addr 4 2 4\n" +
                "seti 2 8 4\n" +
                "addi 3 1 3\n" +
                "gtrr 3 5 2\n" +
                "addr 2 4 4\n" +
                "seti 1 4 4\n" +
                "mulr 4 4 4\n" +
                "addi 5 2 5\n" +
                "mulr 5 5 5\n" +
                "mulr 4 5 5\n" +
                "muli 5 11 5\n" +
                "addi 2 5 2\n" +
                "mulr 2 4 2\n" +
                "addi 2 18 2\n" +
                "addr 5 2 5\n" +
                "addr 4 0 4\n" +
                "seti 0 6 4\n" +
                "setr 4 8 2\n" +
                "mulr 2 4 2\n" +
                "addr 4 2 2\n" +
                "mulr 4 2 2\n" +
                "muli 2 14 2\n" +
                "mulr 2 4 2\n" +
                "addr 5 2 5\n" +
                "seti 0 1 0\n" +
                "seti 0 5 4").calculate());
    }

}