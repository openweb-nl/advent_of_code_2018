package com.aharpour.adventofcode;

import java.util.HashSet;
import java.util.Set;

import static com.aharpour.adventofcode.utils.StringUtils.stringToIntArray;

public class Day1Question2 {

    public static int calculate(String string) {
        int sum = 0;
        Set<Integer> frequencies = new HashSet<>();
        frequencies.add(sum);
        int[] ints = stringToIntArray(string, "\\s+");
        int length = ints.length;
        for (int i = 0; true; i++) {
            frequencies.add(sum);
            sum += ints[i % length];
            if (frequencies.contains(sum)) {
                return sum;
            }
        }
    }

}