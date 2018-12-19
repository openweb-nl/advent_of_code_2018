package com.gklijs.adventofcode.day18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gklijs.adventofcode.Utils;
import com.gklijs.adventofcode.utils.Triple;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day18 {

    private Day18() {
        //prevent instantiation
    }

    public static Single<String> afterTen(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Utils::addString)
            .map(Utils::toArrayOfArrays)
            .map(Day18::tenUpdates)
            .map(Day18::response);
    }

    public static Single<String> afterBillion(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Utils::addString)
            .map(Utils::toArrayOfArrays)
            .map(Day18::billionUpdates)
            .map(Day18::response);
    }

    private static char[][] tenUpdates(char[][] input) {
        char[][] result = input;
        for (int i = 0; i < 10; i++) {
            result = update(result);
        }
        return result;
    }

    private static boolean equals(char[][] history, char[][] result) {
        for (int y = 0; y < result.length; y++) {
            if (!Arrays.equals(history[y], result[y])) {
                return false;
            }
        }
        return true;
    }

    private static char[][] billionUpdates(char[][] input) {
        char[][] result = input;
        List<char[][]> history = new ArrayList<>();
        for (long i = 0L; i < 1_000_000_000L; i++) {
            result = update(result);
            if (i != 0 && i % 100 == 0) {
                history.add(result);
            }
            for (int n = 0; n < history.size(); n++) {
                if (equals(history.get(n), result)) {
                    long cycle = i - ((n + 1) * 100);
                    if (cycle == 0) {
                        continue;
                    }
                    long cycles = (1_000_000_000L - i) / cycle;
                    i += cycles * cycle;
                }
            }
        }
        return result;
    }

    private static char newValue(char[][] input, int y, int x) {
        Acre oldValue = Acre.get(input[y][x]);
        Triple<Integer, Integer, Integer> counter = new Triple<>(0, 0, 0);
        if (y != 0) {
            for (int x2 = Math.max(0, x - 1); x2 < Math.min(input[y].length, x + 2); x2++) {
                Acre neighbour = Acre.get(input[y - 1][x2]);
                neighbour.update(counter);
            }
        }
        if (x != 0) {
            Acre neighbour = Acre.get(input[y][x - 1]);
            neighbour.update(counter);
        }
        if (x != input[y].length - 1) {
            Acre neighbour = Acre.get(input[y][x + 1]);
            neighbour.update(counter);
        }
        if (y != input.length - 1) {
            for (int x2 = Math.max(0, x - 1); x2 < Math.min(input[y].length, x + 2); x2++) {
                Acre neighbour = Acre.get(input[y + 1][x2]);
                neighbour.update(counter);
            }
        }
        return oldValue.next(counter).getValue();
    }

    private static char[][] update(char[][] input) {
        char[][] result = new char[input.length][input[0].length];
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                result[y][x] = newValue(input, y, x);
            }
        }
        return result;
    }

    private static String response(char[][] input) {
        int trees = 0;
        int lumberyards = 0;
        for (char[] row : input) {
            for (char value : row) {
                Acre acre = Acre.get(value);
                if (acre == Acre.TREES) {
                    trees++;
                } else if (acre == Acre.LUMBERYARD) {
                    lumberyards++;
                }
            }
        }
        return Integer.toString(trees * lumberyards);
    }
}
