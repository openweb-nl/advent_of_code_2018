package com.aharpour.adventofcode;

import java.util.HashMap;
import java.util.Map;

public abstract class Day12 {

    protected boolean[] pots;
    protected final int numberOfGens;
    private final int margin;
    private Map<Integer, Boolean> rules = new HashMap<>();


    public Day12(int numberOfGens) {
        this.numberOfGens = numberOfGens;
        this.margin = numberOfGens + 2;
    }

    protected void parseInput(String string) {
        String[] split = string.split("\\s*\\n\\s*");
        parsePods(split[0]);
        parseRules(split);
    }

    protected int indexToPotNumber(int i) {
        return i - margin;
    }

    protected void nextGen() {
        boolean[] nextGen = new boolean[pots.length];
        for (int i = 2, oldLength = pots.length - 2; i < oldLength; i++) {
            nextGen[i] = isGreenNextGen(i);
        }
        this.pots = nextGen;
    }

    protected void printPots() {
        for (boolean pot : pots) {
            if (pot) {
                System.out.print('#');
            } else {
                System.out.print('.');
            }
        }
        System.out.println();
    }

    private void parseRules(String[] split) {
        for (int i = 1; i < split.length; i++) {
            String r = split[i];
            String[] p = r.split(" => ");
            rules.put(getRuleNumber(p[0]), getRuleValue(p[1]));
        }
    }

    private Boolean getRuleValue(String input) {
        return input.trim().equals("#");
    }
    
    private boolean isGreenNextGen(int index) {
        boolean result = false;
        Integer i = toInt(pots, index);
        if (rules.containsKey(i)) {
            result = rules.get(i);
        }
        return result;
    }

    private int toInt(boolean[] array, int index) {
        int result = 0;
        if (array[index - 2]) {
            result += 10000;
        }
        if (array[index - 1]) {
            result += 1000;
        }
        if (array[index]) {
            result += 100;
        }
        if (array[index + 1]) {
            result += 10;
        }
        if (array[index + 2]) {
            result += 1;
        }
        return result;
    }

    private Integer getRuleNumber(String s) {
        char[] chars = s.trim().toCharArray();
        int result = 0;
        if (chars[0] == '#') {
            result += 10000;
        }
        if (chars[1] == '#') {
            result += 1000;
        }
        if (chars[2] == '#') {
            result += 100;
        }
        if (chars[3] == '#') {
            result += 10;
        }
        if (chars[4] == '#') {
            result += 1;
        }
        return result;
    }

    private void parsePods(String s) {
        char[] input = s.substring("initial state: ".length()).trim().toCharArray();
        pots = new boolean[input.length + 2 * margin];
        for (int i = 0, inputLength = input.length; i < inputLength; i++) {
            char c = input[i];
            pots[margin + i] = c == '#';
        }
    }
}