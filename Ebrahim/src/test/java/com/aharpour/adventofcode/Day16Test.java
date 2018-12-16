package com.aharpour.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day16Test {

    private Day16 day16 = new Day16();

    @Test
    public void applyOpcode() {
        day16.setRegisters(3, 2, 1, 1);
        day16.applyOpcode(2, 2, 1, 2);
        Assert.assertTrue(day16.isRegistersSetTo(3, 2, 2, 1));
    }
}