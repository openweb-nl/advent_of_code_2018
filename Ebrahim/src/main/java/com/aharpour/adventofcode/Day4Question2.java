package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.IntPair;
import com.aharpour.adventofcode.utils.Pair;

import java.util.Comparator;

public class Day4Question2 extends Day4 {

    public int calculate(String string) {
        parseInput(string);
        return schedule.entrySet().stream()
                .map(e -> {
                    int key = e.getKey();
                    int[] acuSleep = getGuardAcuSleep(e.getValue());
                    return new Pair<Integer, IntPair>(key, getMax(acuSleep));
                })
                .max(Comparator.comparing(i -> i.getValue().getValue()))
                .map(i -> i.getKey() * i.getValue().getKey())
                .orElseThrow(IllegalArgumentException::new);
    }

}