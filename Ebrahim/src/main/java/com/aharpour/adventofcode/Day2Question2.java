package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.SimpleArrayBiReducer;

import static com.aharpour.adventofcode.utils.StringUtils.intersection;

public class Day2Question2 {

    public static String calculate(String string) {
        return SimpleArrayBiReducer.<String, String>builder()
                .operator(Day2Question2::operator)
                .build().operate(string.split("\\s+"))
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


    private static String operator(String first, String second) {
        String result = null;
        String intersection = intersection(first, second);
        if (first.length() == second.length() && second.length() == intersection.length() + 1) {
            result = intersection;
        }
        return result;
    }
}