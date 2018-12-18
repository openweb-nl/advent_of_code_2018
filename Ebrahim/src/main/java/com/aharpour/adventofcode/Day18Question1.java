package com.aharpour.adventofcode;

public class Day18Question1 extends Day18 {

    public Day18Question1(String input) {
        super(input);
    }

    public int calculate() {
        for (int i = 0; i < 10; i++) {
            nextMinute();
        }
        int[] stat = getStat();
        return stat[1] * stat[2];
    }
}
