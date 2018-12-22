package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

/**
 * @author Ebrahim Aharpour
 * @since 12/22/2018
 */
public class Day22 {
    protected final int depth;
    protected final IntPair target;
    private final int[][] erosion;
    protected final int[][] cave;

    public Day22(int depth, IntPair target, int margin) {
        this.depth = depth;
        this.target = target;
        this.cave = new int[target.getKey() + 1 + margin][target.getValue() + 1 + margin];
        this.erosion = new int[cave.length][cave[0].length];
        calculateType();
    }

    private void calculateType() {
        for (int x = 0; x < cave.length; x++) {
            for (int y = 0; y < cave[0].length; y++) {
                erosion[x][y] = calculateErosion(x, y);
                cave[x][y] = calculateType(x, y);
            }
        }
    }

    public void print() {
        for (int y = 0; y < cave[0].length; y++) {
            for (int x = 0; x < cave.length; x++) {
                System.out.print(typeToChar(cave[x][y]));
            }
            System.out.println();
        }
    }

    private char typeToChar(int i) {
        char c;
        switch (i) {
            case 0:
                c = '.';
                break;
            case 1:
                c = '=';
                break;
            case 2:
                c = '|';
                break;
            default:
                throw new IllegalArgumentException();
        }
        return c;
    }

    private int calculateType(int x, int y) {
        return erosion[x][y] % 3;
    }

    private int calculateErosion(int x, int y) {
        long index = 0;
        if (target.getKey() != x || target.getValue() != y) {
            if (y == 0) {
                index = x * 16807;
            } else if (x == 0) {
                index = y * 7905;
            } else {
                index = erosion[x - 1][y] * erosion[x][y - 1];
            }

        }
        return new Long((index + depth) % 20183).intValue();
    }
}