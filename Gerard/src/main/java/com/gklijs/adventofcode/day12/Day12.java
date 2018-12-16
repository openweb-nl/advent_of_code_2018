package com.gklijs.adventofcode.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.gklijs.adventofcode.utils.Pair;
import com.gklijs.adventofcode.utils.Triple;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day12 {

    private Day12() {
        //prevent instantiation
    }

    public static Single<String> plantIndex(Observable<String> input) {
        return input
            .reduce(new Pair<>(null, new HashMap<>()), Day12::update)
            .map(pair -> new Triple<>(pair.getFirst(), pair.getSecond(), 0))
            .map(Day12::doTwenty)
            .map(Day12::getSum)
            .map(Objects::toString);
    }

    public static Single<String> plantIndexTwo(Observable<String> input) {
        return input
            .reduce(new Pair<>(null, new HashMap<>()), Day12::update)
            .map(pair -> new Triple<>(pair.getFirst(), pair.getSecond(), 0))
            .map(Day12::doFiftyBillion)
            .map(Objects::toString);
    }

    private static int getKey(boolean first, boolean second, boolean third, boolean fourth, boolean fifth) {
        int result = 0;
        if (first) {
            result += 16;
        }
        if (second) {
            result += 8;
        }
        if (third) {
            result += 4;
        }
        if (fourth) {
            result += 2;
        }
        if (fifth) {
            result += 1;
        }
        return result;
    }

    private static Pair<boolean[], Map<Integer, Boolean>> update(Pair<boolean[], Map<Integer, Boolean>> result, String line) {
        if (line.isEmpty()) {
            return result;
        }
        if (result.getFirst() == null) {
            char[] chars = line.toCharArray();
            List<Boolean> values = new ArrayList<>(line.length());
            for (char c : chars) {
                if (c == '#') {
                    values.add(true);
                } else if (c == '.') {
                    values.add(false);
                }
            }
            boolean[] first = new boolean[values.size()];
            for (int i = 0; i < values.size(); i++) {
                first[i] = values.get(i);
            }
            result.setFirst(first);
        } else {
            char[] chars = line.toCharArray();
            int key = getKey(chars[0] == '#', chars[1] == '#', chars[2] == '#', chars[3] == '#', chars[4] == '#');
            result.getSecond().put(key, chars[chars.length - 1] == '#');
        }
        return result;
    }

    private static Triple<boolean[], Map<Integer, Boolean>, Integer> doTwenty(Triple<boolean[], Map<Integer, Boolean>, Integer> input) {
        Triple<boolean[], Map<Integer, Boolean>, Integer> result = input;
        for (int x = 0; x < 20; x++) {
            result = newGen(result);
        }
        return result;
    }

    private static long doFiftyBillion(Triple<boolean[], Map<Integer, Boolean>, Integer> input) {
        Triple<boolean[], Map<Integer, Boolean>, Integer> result = input;
        int oldSum = getSum(input);
        int counter = 0;
        int[] deltas = new int[]{1, 2, 3};
        while (deltas[0] != deltas[1] || deltas[1] != deltas[2]) {
            counter++;
            result = newGen(result);
            int newSum = getSum(result);
            deltas[counter % 3] = newSum - oldSum;
            oldSum = newSum;
        }
        return (50_000_000_000L - counter) * deltas[0] + oldSum;
    }

    private static boolean safeGet(boolean[] ia, int index) {
        if (index < 0 || index >= ia.length) {
            return false;
        } else {
            return ia[index];
        }
    }

    private static Triple<boolean[], Map<Integer, Boolean>, Integer> newGen(Triple<boolean[], Map<Integer, Boolean>, Integer> input) {
        boolean[] current = input.getFirst();
        int prepend = 0;
        if (current[0]) {
            prepend = 2;
        } else if (current[1]) {
            prepend = 1;
        }
        int append = 0;
        if (current[current.length - 1]) {
            append = 2;
        } else if (current[current.length - 2]) {
            append = 1;
        }
        boolean[] next = new boolean[current.length + append + prepend];
        for (int x = 0; x < prepend; x++) {
            int key = getKey(false, false, false, safeGet(current, 0 - x), current[1 - x]);
            if (input.getSecond().containsKey(key) && input.getSecond().get(key)) {
                next[prepend - (1 + x)] = true;
            }
        }
        for (int x = 0; x < current.length; x++) {
            int key = getKey(safeGet(current, x - 2), safeGet(current, x - 1), current[x], safeGet(current, x + 1), safeGet(current, x + 2));
            if (input.getSecond().containsKey(key) && input.getSecond().get(key)) {
                next[x + prepend] = true;
            }
        }
        for (int x = 0; x < append; x++) {
            int key = getKey(current[(current.length - 2) + x], safeGet(current, (current.length - 1) + x), false, false, false);
            if (input.getSecond().containsKey(key) && input.getSecond().get(key)) {
                next[x + prepend + current.length] = true;
            }
        }
        input.setFirst(next);
        input.setThird(input.getThird() + prepend);
        return input;
    }

    private static int getSum(Triple<boolean[], Map<Integer, Boolean>, Integer> input) {
        int sum = 0;
        for (int x = 0; x < input.getFirst().length; x++) {
            if (input.getFirst()[x]) {
                sum += (x - input.getThird());
            }
        }
        return sum;
    }
}
