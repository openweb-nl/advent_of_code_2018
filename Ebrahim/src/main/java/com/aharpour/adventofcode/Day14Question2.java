package com.aharpour.adventofcode;


import java.util.ArrayList;

public class Day14Question2 extends Day14 {


    private int[] target;

    public Day14Question2() {
        super(new ArrayList<>(10000));
    }

    public int calculate(String input) {
        parseInput(input);
        int result;
        while ((result = scanForMatch()) == -1) {
            nextStep();
        }
        return result;
    }

    private void parseInput(String input) {
        target = input.chars()
                .map(c -> c - 48).toArray();
    }

    private int scanForMatch() {
        int result = -1;
        int size = recipes.size();
        if (size > target.length) {
            result = matchesAt(size - target.length);
        }
        if (result == -1 && size > target.length + 1) {
            result = matchesAt(size - (target.length + 1));
        }
        return result;
    }

    private int matchesAt(int index) {
        int result = index;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != recipes.get(i + (result))) {
                result = -1;
                break;
            }
        }
        return result;
    }

}