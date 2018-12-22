package com.gklijs.adventofcode.day19day21;

import java.util.ArrayList;
import java.util.List;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day21 {

    private Day21() {
        //prevent instantiation
    }

    public static Single<String> min(Observable<String> input) {
        return input
            .reduce(new Pair<>(null, new ArrayList<>()), Day19::update)
            .map(Day21::min);
    }

    public static Single<String> max(Observable<String> input) {
        return input
            .reduce(new Pair<>(null, new ArrayList<>()), Day19::update)
            .map(Day21::max);
    }

    private static List<Integer> execute(Pair<Integer, List<Pair<Opcode, int[]>>> input, int[] initial) {
        List<Integer> l = new ArrayList<>();
        List<Pair<Opcode, int[]>> options = input.getSecond();
        int[] value = initial.clone();
        while (value[input.getFirst()] >= 0 && value[input.getFirst()] < options.size()) {
            Pair<Opcode, int[]> p = options.get(value[input.getFirst()]);
            p.getFirst().apply(p.getSecond(), value);
            value[input.getFirst()] += 1;
            if (value[1] == 28) {
                if (l.contains(value[4])) {
                    return l;
                } else {
                    l.add(value[4]);
                }
            }
        }
        return l;
    }

    private static String min(Pair<Integer, List<Pair<Opcode, int[]>>> input) {
        List<Integer> l = execute(input, new int[]{0, 0, 0, 0, 0, 0});
        return Long.toString(l.get(0));
    }

    private static String max(Pair<Integer, List<Pair<Opcode, int[]>>> input) {
        List<Integer> l = execute(input, new int[]{0, 0, 0, 0, 0, 0});
        return Long.toString(l.get(l.size() - 1));
    }
}
