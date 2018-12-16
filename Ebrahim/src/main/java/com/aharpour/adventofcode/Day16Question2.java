package com.aharpour.adventofcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Day16Question2 extends Day16Question1 {


    public Day16Question2(String string) {
        super(string);
    }

    @Override
    public int calculate() {
        Map<Integer, Integer> dictionary = calculateOpcodeDictionary();

        setRegisters(0, 0, 0, 0);
        for (int[] ints : program) {
            applyOpcode(dictionary.get(ints[0]), ints[1], ints[2], ints[3]);
        }

        return getRegisters()[0];
    }

    private Map<Integer, Integer> calculateOpcodeDictionary() {
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        for (Sample sample : samples) {
            Integer opcode = sample.getInputs()[0];
            if (map.containsKey(opcode)) {
                HashSet<Integer> candidates = map.get(opcode);
                int size = candidates.size();
                if (size == 0) {
                    throw new IllegalArgumentException();
                } else if (size > 1) {
                    candidates.retainAll(opcodesMatches(sample));
                    map.put(opcode, candidates);
                }
            } else {
                map.put(opcode, opcodesMatches(sample));
            }
        }
        return toMap(map, new HashMap<>(), 0, new HashSet<>());
    }

    private Map<Integer, Integer> toMap(Map<Integer, HashSet<Integer>> multimap, HashMap<Integer, Integer> map, int index, HashSet<Integer> taken) {
        for (int op = index; op < 16; op++) {
            HashSet<Integer> candidates = (HashSet<Integer>) multimap.get(op).clone();
            candidates.removeAll(taken);
            if (candidates.size() == 0) {
                throw new IllegalArgumentException("Out of candidates");
            } else if (candidates.size() == 1) {
                Integer next = candidates.iterator().next();
                taken.add(next);
                map.put(op, next);
            } else {
                for (Integer candidate : candidates) {
                    try {
                        HashMap<Integer, Integer> clone = (HashMap<Integer, Integer>) map.clone();
                        HashSet<Integer> takenClone = (HashSet<Integer>) taken.clone();
                        clone.put(op, candidate);
                        takenClone.add(candidate);
                        Map<Integer, Integer> integerIntegerMap = toMap(multimap, clone, op + 1, takenClone);
                        return integerIntegerMap;
                    } catch (IllegalArgumentException e) {
                    }
                }
                throw new IllegalArgumentException("Out of candidates");

            }

        }
        return map;
    }
}
