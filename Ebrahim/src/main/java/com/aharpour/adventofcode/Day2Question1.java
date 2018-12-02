package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.BooleanPair;
import com.aharpour.adventofcode.utils.IntPair;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day2Question1 {

    public static int calculate(String string) {
        return Arrays.stream(string.split("\\s+"))
                .map(Day2Question1::letterCount)
                .map(Day2Question1::hasTwoAndThree)
                .map(BooleanPair::toIntPair)
                .reduce(IntPair::combine)
                .map(counts -> counts.getKey() * counts.getValue())
                .orElse(0);
    }

    private static BooleanPair hasTwoAndThree(Map<Integer, Long> map) {
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(v -> v > 1 && v < 4)
                .map(n -> new BooleanPair(n == 2, n == 3))
                .reduce(new BooleanPair(false, false), BooleanPair::combine);
    }

    private static Map<Integer, Long> letterCount(String line) {
        return line.chars().boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
    }


}
