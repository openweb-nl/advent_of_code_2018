package com.gklijs.adventofcode.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day19 {

    private Day19() {
        //prevent instantiation
    }

    public static Single<String> first(Observable<String> input) {
        return input
            .reduce(new Pair<>(null, new ArrayList<>()), Day19::update)
            .map(r -> execute(r, new int[6]))
            .map(Objects::toString);
    }

    public static Single<String> second(Observable<String> input) {
        return input
            .reduce(new Pair<>(null, new ArrayList<>()), Day19::update)
            .map(r -> execute(r, new int[]{1, 0, 0, 0, 0, 0}))
            .map(Objects::toString);
    }

    private static Pair<Integer, List<Pair<Opcode, int[]>>> update(Pair<Integer, List<Pair<Opcode, int[]>>> result, String input) {
        StringBuilder builder = new StringBuilder();
        if (input.charAt(0) == '#') {
            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    builder.append(c);
                }
            }
            result.setFirst(Integer.parseInt(builder.toString()));
        } else {
            int counter = 0;
            Opcode opcode = Opcode.get(input.substring(0, 4));
            int[] ops = new int[3];
            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    builder.append(c);
                } else if (builder.length() != 0) {
                    ops[counter] = Integer.parseInt(builder.toString());
                    builder.setLength(0);
                    counter++;
                }
            }
            if (builder.length() != 0) {
                ops[counter] = Integer.parseInt(builder.toString());
            }
            result.getSecond().add(new Pair<>(opcode, ops));
        }
        return result;
    }

    private static int execute(Pair<Integer, List<Pair<Opcode, int[]>>> input, int[] initial) {
        List<Pair<Opcode, int[]>> options = input.getSecond();
        int[] value = initial.clone();
        while (value[input.getFirst()] >= 0 && value[input.getFirst()] < options.size()) {
            Pair<Opcode, int[]> p = options.get(value[input.getFirst()]);
            p.getFirst().apply(p.getSecond(), value);
            value[input.getFirst()] += 1;
            if (value[input.getFirst()] == 9) {
                int result = 0;
                int till = (int) Math.round(Math.sqrt(value[5])) + 1;
                for (int i = 1; i < till; i++) {
                    if (value[5] % i == 0) {
                        result += i;
                        if (i * i != value[5]) {
                            result += value[5] / i;
                        }
                    }
                }
                return result;
            }
        }
        return value[0];
    }
}
