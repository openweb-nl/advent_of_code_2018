package com.aharpour.adventofcode;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day16Question1 extends Day16 {

    private static final int BEFORE = "Before: [".length();
    private static final int AFTER = "After:  [".length();
    protected List<Sample> samples = new ArrayList<>();
    protected List<int[]> program = new ArrayList<>();

    public Day16Question1(String string) {
        parseInputs(string);
    }

    public int calculate() {
        int result = 0;
        for (Sample sample : samples) {
            int size = opcodesMatches(sample).size();
            if (size >= 3) {
                result++;
            }
        }

        return result;
    }

    public HashSet<Integer> opcodesMatches(Sample sample) {
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < 16; i++) {
            try {
                setRegisters(sample.registersBefore.clone());
                applyOpcode(i, sample.inputs[1], sample.inputs[2], sample.inputs[3]);
                if (assertArrayEquals(sample.registersAfter, getRegisters())) {
                    result.add(i);
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    private boolean assertArrayEquals(int[] registersAfter, int[] registers) {
        return registers[0] == registersAfter[0] &&
                registers[1] == registersAfter[1] &&
                registers[2] == registersAfter[2] &&
                registers[3] == registersAfter[3];
    }

    private void parseInputs(String string) {
        String[] split = string.split("\\s*\\n\\s*");
        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("Before: ")) {
                int[] registersBefore = parseRegisters(split[i], BEFORE);
                i++;
                int[] operations = stringArrayToIntArray(split[i].split(" "));
                i++;
                int[] registersAfter = parseRegisters(split[i], AFTER);
                samples.add(new Sample(registersBefore, registersAfter, operations));
            } else if (split[i].length() > 0) {
                program.add(stringArrayToIntArray(split[i].split(" ")));
            }
        }

    }

    private int[] parseRegisters(String s, int prefix) {
        String[] split = s.substring(prefix, s.length() - 1).split(", ");
        return stringArrayToIntArray(split);
    }

    private int[] stringArrayToIntArray(String[] array) {
        if (array.length != 4) {
            throw new IllegalArgumentException();
        }
        return new int[]{Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3])};
    }


    @Data
    public class Sample {
        private final int[] registersBefore;
        private final int[] registersAfter;
        private final int[] inputs;
    }
}
