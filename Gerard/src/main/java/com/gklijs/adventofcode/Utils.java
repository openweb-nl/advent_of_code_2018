package com.gklijs.adventofcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Flowable;

public class Utils {

    private Utils() {
        //prevent instantiation
    }

    static Flowable<String> readLines(String fileName) {
        return Flowable.generate(
            () -> new BufferedReader(new InputStreamReader(Objects.requireNonNull(Utils.class.getClassLoader().getResourceAsStream(fileName)))),
            (reader, emitter) -> {
                final String line = reader.readLine();
                if (line != null) {
                    emitter.onNext(line);
                } else {
                    emitter.onComplete();
                }
            },
            BufferedReader::close);
    }

    public static <T> Pair<Set<T>, T> firstDuplicate(Pair<Set<T>, T> pair, T value) {
        if (pair.getFirst().contains(value)) {
            pair.setSecond(value);
        } else {
            pair.getFirst().add(value);
        }
        return pair;
    }

    public static <T> Map<T, Integer> toFrequencyMap(Collection<T> collection) {
        Map<T, Integer> frequencyMap = new HashMap<>();
        addToFrequencyMap(frequencyMap, collection);
        return frequencyMap;
    }

    public static <T> void addToFrequencyMap(Map<T, Integer> frequencyMap, Collection<T> collection) {
        for (T item : collection) {
            if (frequencyMap.containsKey(item)) {
                frequencyMap.put(item, frequencyMap.get(item) + 1);
            } else {
                frequencyMap.put(item, 1);
            }
        }
    }

    public static <T> void addToFrequencyMap(Map<T, Integer> frequencyMap, T item) {
        if (frequencyMap.containsKey(item)) {
            frequencyMap.put(item, frequencyMap.get(item) + 1);
        } else {
            frequencyMap.put(item, 1);
        }
    }

    public static List<Character> toList(String input) {
        List<Character> charList = new ArrayList<>();
        for (char c : input.toCharArray()) {
            charList.add(c);
        }
        return charList;
    }

    public static List<char[]> addString(List<char[]> lines, String line) {
        lines.add(line.toCharArray());
        return lines;
    }

    public static char[][] toArrayOfArrays(List<char[]> lines) {
        char[][] result = new char[lines.size()][];
        for (int y = 0; y < result.length; y++) {
            result[y] = lines.get(y);
        }
        return result;
    }
}
