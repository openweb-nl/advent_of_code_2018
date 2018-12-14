package com.gklijs.adventofcode.day14;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day14 {

    private Day14() {
        //prevent instantiation
    }

    public static Single<String> tenAfter(Observable<String> input) {
        return input
            .lastOrError()
            .map(Integer::parseInt)
            .map(recipes -> new RecipeList(recipes + 10))
            .map(RecipeList::complete)
            .map(Day14::printLastTen);
    }

    public static Single<Integer> doTill(Observable<String> input) {
        return input
            .lastOrError()
            .map(sequence -> new Pair<>(new RecipeList(100000000), sequence))
            .map(pair -> pair.getFirst().complete(pair.getSecond()));
    }

    private static String printLastTen(int[] scores) {
        StringBuilder builder = new StringBuilder();
        for (int i = scores.length - 10; i < scores.length; i++) {
            builder.append(scores[i]);
        }
        return builder.toString();
    }
}
