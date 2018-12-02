package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.IntPair;
import com.aharpour.adventofcode.utils.Pair;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day2Question1 {

    public static int calculate(String string) {
        IntPair counts = Arrays.stream(string.split("\\s+"))
                .map(Day2Question1::letterCount)
                .map(Day2Question1::counts)
                .reduce(new IntPair(0, 0), (i, b) -> {
                    if (b.getKey()) {
                        i.incrementKey();
                    }
                    if (b.getValue()) {
                        i.incrementValue();
                    }
                    return i;
                }, (p1, p2) -> new IntPair(p1.getKey() + p2.getKey(), p1.getValue() + p2.getValue()));
        return counts.getKey() * counts.getValue();
    }

    private static Pair<Boolean, Boolean> counts(Map<Integer, Long> map) {
        return map.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(v -> v > 1 && v < 4)
                .reduce(new Pair<>(false, false), (p, n) -> {
                            if (n == 2) {
                                p.setKey(true);
                            } else {
                                p.setValue(true);
                            }
                            return p;
                        }
                        , (p1, p2) -> new Pair<>(p1.getKey() || p2.getKey(), p1.getValue() || p2.getValue()));

    }

    private static Map<Integer, Long> letterCount(String line) {
        return line.chars().boxed()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
    }


}
