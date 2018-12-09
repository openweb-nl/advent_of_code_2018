package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.datastructue.IntStatefulLinkedList;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9Question1 {


    private static final Pattern PATTERN = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points");
    private int lastMarbleWorth;
    private long[] scores;

    public long calculate(String string) {
        parseInput(string);
        MarbleGame marbleGame = new MarbleGame();
        for (int i = 0; i < lastMarbleWorth; i++) {
            scores[i % scores.length] = scores[i % scores.length] + marbleGame.placeMarble();
        }
        return Arrays.stream(scores).max().orElseThrow(IllegalArgumentException::new);
    }

    private void parseInput(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.find()) {
            scores = new long[Integer.parseInt(matcher.group(1))];
            lastMarbleWorth = Integer.parseInt(matcher.group(2));
        } else {
            throw new IllegalArgumentException();
        }

    }

    class MarbleGame {
        private int marble = 1;
        private IntStatefulLinkedList list = new IntStatefulLinkedList();

        private MarbleGame() {
            list.insert(0);
        }

        int placeMarble() {
            int result = 0;
            if (marble % 23 != 0) {
                list.forward(1);
                list.insert(marble);
            } else {
                list.backward(7);
                result = marble + list.remove();
            }
            marble++;
            return result;
        }


    }


}