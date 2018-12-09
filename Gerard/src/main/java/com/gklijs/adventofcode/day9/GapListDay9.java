package com.gklijs.adventofcode.day9;

import java.util.Arrays;
import java.util.stream.IntStream;

import com.gklijs.adventofcode.errors.InvalidInputException;
import com.gklijs.adventofcode.utils.GapList;
import com.gklijs.adventofcode.utils.Triple;
import io.reactivex.Observable;
import io.reactivex.Single;

public class GapListDay9 {

    private GapListDay9() {
        //prevent instantiation
    }

    public static Single<Long> winningScore(Observable<String> input) {
        return input
            .lastOrError()
            .map(i -> playGame(i, 1))
            .map(GapListDay9::heigestScore);
    }

    public static Single<Long> winningScore(Observable<String> input, int multiplier) {
        return input
            .lastOrError()
            .map(i -> playGame(i, multiplier))
            .map(GapListDay9::heigestScore);
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

    private static Triple<int[], GapList<Integer>, long[]> initialState(int[] input) {
        // first is the index of the current marble, second the id of the player about to play
        int[] trackers = new int[]{0, input[0]};
        GapList<Integer> marblesInPlay = new GapList<>(input[1]);
        marblesInPlay.add(0);
        long[] scores = new long[input[0]];
        return new Triple<>(trackers, marblesInPlay, scores);
    }

    private static void updateState(Triple<int[], GapList<Integer>, long[]> state, int marble) {
        int player = state.getFirst()[1] + 1;
        player = player >= state.getThird().length ? 0 : player;
        state.getFirst()[1] = player;
        if (state.getSecond().size() <= 1) {
            state.getSecond().add(marble);
            state.getFirst()[0] = 1;
        } else if (marble % 23 == 0) {
            int index = (state.getFirst()[0] + state.getSecond().size() - 7) % state.getSecond().size();
            int removedValue = state.getSecond().remove(index);
            state.getFirst()[0] = index;
            state.getThird()[player] = state.getThird()[player] + marble + removedValue;
        } else {
            int index = (state.getFirst()[0] + 2) % state.getSecond().size();
            state.getSecond().add(index, marble);
            state.getFirst()[0] = index;
        }
    }

    private static Triple<int[], GapList<Integer>, long[]> playGame(String input, int multiplier) {
        int[] playersAndMarbles = playersAndMarbles(input, multiplier);
        Triple<int[], GapList<Integer>, long[]> state = initialState(playersAndMarbles);
        IntStream.range(1, playersAndMarbles[1] + 1).forEach(marble -> updateState(state, marble));
        return state;
    }

    private static long heigestScore(Triple<int[], GapList<Integer>, long[]> input) {
        return Arrays.stream(input.getThird()).max().orElse(-1L);
    }
}
