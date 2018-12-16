package com.gklijs.adventofcode.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day16 {

    private Day16() {
        //prevent instantiation
    }

    public static Single<String> threeOrMoreMatches(Observable<String> input) {
        return input
            .reduce(new Pair<>(new ArrayList<>(), new ArrayList<>()), Day16::addLine)
            .map(Pair::getFirst)
            .map(x -> x.stream().filter(s -> Day16.matchingOpcodes(s).size() >= 3).count())
            .map(Objects::toString);
    }

    public static Single<String> getResult(Observable<String> input) {
        return input
            .reduce(new Pair<>(new ArrayList<>(), new ArrayList<>()), Day16::addLine)
            .map(p -> new Pair<>(getMapping(p.getFirst()), p.getSecond()))
            .map(Day16::execute)
            .map(r -> Integer.toString(r[0]));
    }

    private static List<Opcode> matchingOpcodes(int[][] sample) {
        List<Opcode> opcodes = new ArrayList<>();
        for (Opcode opcode : Opcode.values()) {
            int a = sample[1][1];
            int b = sample[1][2];
            int c = sample[1][3];
            int[] result = opcode.apply(a, b, c, sample[0]);
            if (Arrays.equals(result, sample[2])) {
                opcodes.add(opcode);
            }
        }
        return opcodes;
    }

    private static Map<Integer, Opcode> getMapping(List<int[][]> examples) {
        Map<Integer, List<Opcode>> mappings = new HashMap<>();
        for (int[][] example : examples) {
            int opcode = example[1][0];
            List<Opcode> possibleCodes = matchingOpcodes(example);
            if (mappings.containsKey(opcode)) {
                mappings.get(opcode).retainAll(possibleCodes);
            } else {
                mappings.put(opcode, possibleCodes);
            }
        }
        Map<Integer, Opcode> result = new HashMap<>();
        while (result.size() < Opcode.values().length) {
            for (int i = 0; i < Opcode.values().length; i++) {
                if (mappings.containsKey(i) && mappings.get(i).size() == 1) {
                    Opcode opcode = mappings.get(i).get(0);
                    result.put(i, opcode);
                    mappings.remove(i);
                    for (Map.Entry<Integer, List<Opcode>> entry : mappings.entrySet()) {
                        entry.getValue().remove(opcode);
                    }
                }
            }
        }
        return result;
    }

    private static int[] execute(Pair<Map<Integer, Opcode>, List<int[]>> input) {
        int[] result = new int[4];
        for (int[] instruction : input.getSecond()) {
            Opcode opcode = input.getFirst().get(instruction[0]);
            int a = instruction[1];
            int b = instruction[2];
            int c = instruction[3];
            result = opcode.apply(a, b, c, result);
        }
        return result;
    }

    private static Pair<List<int[][]>, List<int[]>> addLine(Pair<List<int[][]>, List<int[]>> result, String input) {
        if (input.isEmpty() || input.equals("\r")) {
            return result;
        }
        int[] row = new int[4];
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c) || c == '-') {
                builder.append(c);
            } else if (builder.length() != 0) {
                row[counter] = Integer.parseInt(builder.toString());
                builder.setLength(0);
                counter++;
            }
        }
        if (builder.length() != 0) {
            row[counter] = Integer.parseInt(builder.toString());
            builder.setLength(0);
        }
        if (input.charAt(0) == 'A' && result.getSecond().size() == 2) {
            int[][] sample = new int[3][];
            sample[0] = result.getSecond().get(0);
            sample[1] = result.getSecond().get(1);
            sample[2] = row;
            result.getFirst().add(sample);
            result.changeSecond(l -> {
                l.clear();
                return l;
            });
        } else {
            result.getSecond().add(row);
        }
        return result;
    }
}
