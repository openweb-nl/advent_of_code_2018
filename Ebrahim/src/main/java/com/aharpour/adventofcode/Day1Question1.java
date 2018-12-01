package com.aharpour.adventofcode;

import java.util.Arrays;

import static com.aharpour.adventofcode.utils.StringUtils.stringToIntArray;

public class Day1Question1 {

    public static int calculate(String string) {
        return Arrays.stream(stringToIntArray(string, "\\s+")).sum();
    }
}
