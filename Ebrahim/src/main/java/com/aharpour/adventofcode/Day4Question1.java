package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.IntPair;

import java.util.Comparator;

public class Day4Question1 extends Day4 {




    public int calculate(String string) {
        parseInput(string);
        Integer guard = schedule.entrySet().stream()
                .map(e -> {
                    int key = e.getKey();
                    int sum = e.getValue().stream()
                            .mapToInt(Day::getTotalSleep)
                            .sum();
                    return new IntPair(key, sum);
                })
                .max(Comparator.comparing(IntPair::getValue))
                .map(IntPair::getKey)
                .orElseThrow(IllegalArgumentException::new);
        int[] acuSleep = getGuardAcuSleep(schedule.get(guard));
        IntPair max = getMax(acuSleep);

        return max.getKey() * guard;
    }



}