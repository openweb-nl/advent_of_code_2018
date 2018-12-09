package com.gklijs.adventofcode.day9;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.gklijs.adventofcode.errors.InvalidInputException;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day9 {

    private Day9() {
        //prevent instantiation
    }

    public static Single<Long> winningScore(Observable<String> input) {
        return input
            .lastOrError()
            .map(i -> playGame(i, 1))
            .map(Day9::heightScore);
    }

    public static Single<Long> winningScore(Observable<String> input, int multiplier) {
        return input
            .lastOrError()
            .map(i -> playGame(i, multiplier))
            .map(Day9::heightScore);
    }

    private static int[] playersAndMarbles(String input, int multiplier) {
        int[] result = new int[2];
        boolean firstSet = false;
        StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                builder.append(c);
            } else if (builder.length() != 0) {
                if (!firstSet) {
                    result[0] = Integer.parseInt(builder.toString());
                    builder.setLength(0);
                    firstSet = true;
                } else {
                    result[1] = multiplier * Integer.parseInt(builder.toString());
                    return result;
                }
            }
        }
        throw new InvalidInputException("Could not get players and marbles from string: " + input);
    }

    private static long[] playGame(String input, int multiplier) {
        int[] playersAndMarbles = playersAndMarbles(input, multiplier);
        MarbleGame marbleGame = new MarbleGame(playersAndMarbles[0], playersAndMarbles[1]);
        IntStream.range(1, playersAndMarbles[1] + 1).forEach(marbleGame::play);
        return marbleGame.getScores();
    }

    private static long heightScore(long[] input) {
        return Arrays.stream(input).max().orElse(-1L);
    }
}
