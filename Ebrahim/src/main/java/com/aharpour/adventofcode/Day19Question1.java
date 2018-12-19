package com.aharpour.adventofcode;

public class Day19Question1 extends Day19 {

    public Day19Question1(String input) {
        super(input);
    }

    public int calculate() {
        setRegisters(new int[]{0, 0, 0, 0, 0, 0});
        run();
        return getRegisters()[0];
    }
}
