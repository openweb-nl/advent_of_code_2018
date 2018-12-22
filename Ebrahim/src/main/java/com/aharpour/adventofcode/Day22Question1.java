package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;

/**
 * @author Ebrahim Aharpour
 * @since 12/22/2018
 */
public class Day22Question1 extends Day22 {
    public Day22Question1(int depth, IntPair target) {
        super(depth, target, 0);
    }

    public int calculate() {
        int sum = 0;
        for (int x = 0; x <= target.getKey(); x++) {
            for (int y = 0; y <= target.getValue(); y++) {
                sum += cave[x][y];
            }
        }
        return sum;
    }
}
