package com.gklijs.adventofcode.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import com.gklijs.adventofcode.errors.InvalidInputException;
import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day10 {

    private Day10() {
        //prevent instantiation
    }

    public static Single<String> displayStars(Observable<String> input) {
        return input
            .map(Day10::toStar)
            .collect((Callable<ArrayList<int[]>>) ArrayList::new, List::add)
            .map(Day10::process)
            .map(Pair::getFirst)
            .map(Day10::show);
    }

    public static Single<Integer> stepsNeeded(Observable<String> input) {
        return input
            .map(Day10::toStar)
            .collect((Callable<ArrayList<int[]>>) ArrayList::new, List::add)
            .map(Day10::process)
            .map(Pair::getSecond);
    }

    private static String show(final List<int[]> stars) {
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> coords = new HashMap<>();
        for (int[] star : stars) {
            coords.computeIfPresent(star[1], (x, y) -> {
                y.add(star[0]);
                return y;
            });
            coords.computeIfAbsent(star[1], x -> {
                Set<Integer> set = new HashSet<>();
                set.add(star[0]);
                return set;
            });
            maxX = Math.max(maxX, star[0]);
            minX = Math.min(minX, star[0]);
            maxY = Math.max(maxY, star[1]);
            minY = Math.min(minY, star[1]);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (coords.containsKey(y) && coords.get(y).contains(x)) {
                    builder.append('#');
                } else {
                    builder.append('.');
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private static int[] toStar(String input) {
        int[] result = new int[4];
        StringBuilder builder = new StringBuilder();
        int counter = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c) || c == '-') {
                builder.append(c);
            } else if (builder.length() != 0) {
                result[counter] = Integer.parseInt(builder.toString());
                builder.setLength(0);
                counter++;
            }
            if (counter == 4) {
                return result;
            }
        }
        throw new InvalidInputException("Could not get star from: " + input);
    }

    private static int moveBy(List<int[]> stars, int steps) {
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        for (int[] star : stars) {
            star[0] += steps * star[2];
            star[1] += steps * star[3];
            maxX = Math.max(maxX, star[0]);
            minX = Math.min(minX, star[0]);
        }
        return maxX - minX;
    }

    private static Pair<List<int[]>, Integer> process(List<int[]> input) {
        int counter = 0;
        int oldDiff = Integer.MAX_VALUE;
        int diff = Integer.MAX_VALUE - 1;
        while (oldDiff > diff) {
            oldDiff = diff;
            diff = moveBy(input, 1);
            counter++;
        }
        counter--;
        moveBy(input, -1);
        return new Pair<>(input, counter);
    }
}
