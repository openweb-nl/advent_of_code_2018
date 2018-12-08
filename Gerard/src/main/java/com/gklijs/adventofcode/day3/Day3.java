package com.gklijs.adventofcode.day3;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day3 {

    private Day3() {
        //prevent instantiation
    }

    private static final Set<Integer> ONLY_ZERO = Collections.singleton(0);

    public static Single<Integer> multipleClaims(Observable<String> ids) {
        return ids
            .map(Patch::new)
            .reduce(new int[1000][1000], Day3::claim)
            .map(Day3::claimedMultiple);
    }

    public static Single<Integer> noClaims(Observable<String> ids) {
        return ids
            .map(Patch::new)
            .reduce(new Pair<>(new int[1000][1000], new HashSet<>()), Day3::claim2)
            .map(x -> x.getSecond().iterator().next());
    }

    private static int[][] claim(int[][] fabric, Patch patch) {
        IntStream.range(patch.fromLeft, patch.fromLeft + patch.width).iterator()
            .forEachRemaining((IntConsumer) x -> doForY(patch, y -> fabric[x][y] = fabric[x][y] + 1));
        return fabric;
    }

    private static int claimedMultiple(int[][] fabric) {
        return Arrays.stream(fabric)
            .map(row -> Arrays.stream(row).reduce(0, (o, n) -> n > 1 ? o + 1 : o))
            .reduce(0, Integer::sum);
    }

    private static void setId(int[][] fabric, Patch patch) {
        IntStream.range(patch.fromLeft, patch.fromLeft + patch.width).iterator()
            .forEachRemaining((IntConsumer) x -> doForY(patch, y -> fabric[x][y] = patch.id));
    }

    private static void doForY(Patch patch, IntConsumer c) {
        IntStream.range(patch.fromTop, patch.fromTop + patch.height).iterator()
            .forEachRemaining(c);
    }

    private static Set<Integer> currentIds(int[][] fabric, Patch patch) {
        Set<Integer> ids = new HashSet<>();
        IntStream.range(patch.fromLeft, patch.fromLeft + patch.width).iterator()
            .forEachRemaining((IntConsumer) x -> doForY(patch, y -> ids.add(fabric[x][y])));
        return ids;
    }

    private static Pair<int[][], Set<Integer>> claim2(Pair<int[][], Set<Integer>> pair, Patch patch) {
        Set<Integer> currentClaims = currentIds(pair.getFirst(), patch);
        setId(pair.getFirst(), patch);
        if (ONLY_ZERO.equals(currentClaims)) {
            pair.getSecond().add(patch.id);
        } else {
            pair.getSecond().removeAll(currentClaims);
        }
        return pair;
    }
}
