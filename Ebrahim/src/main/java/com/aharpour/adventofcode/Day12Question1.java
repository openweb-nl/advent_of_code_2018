package com.aharpour.adventofcode;

public class Day12Question1 extends Day12 {

    public Day12Question1(int numberOfGens) {
        super(numberOfGens);
    }


    public int calculate(String input) {
        parseInput(input);
        for (int i = 0; i < this.numberOfGens; i++) {
            nextGen();
        }
        return sumUpPotNumbers();
    }

    private int sumUpPotNumbers() {
        int result = 0;
        boolean[] pots1 = this.pots;
        for (int i = 0, pots1Length = pots1.length; i < pots1Length; i++) {
            if (pots1[i]) {
                result += indexToPotNumber(i);
            }
        }

        return result;
    }
}