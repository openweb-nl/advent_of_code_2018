package com.gklijs.adventofcode.day13;

import java.util.function.UnaryOperator;

import com.gklijs.adventofcode.utils.Pair;

enum Direction {
    UP('^', pair -> pair.changeSecond(y -> y - 1)),
    DOWN('v', pair -> pair.changeSecond(y -> y + 1)),
    LEFT('<', pair -> pair.changeFirst(x -> x - 1)),
    RIGHT('>', pair -> pair.changeFirst(x -> x + 1));

    private final char c;
    private final UnaryOperator<Pair<Integer, Integer>> next;

    Direction(final char c, final UnaryOperator<Pair<Integer, Integer>> next) {
        this.c = c;
        this.next = next;
    }

    static Direction get(char value) {
        for (Direction type : values()) {
            if (type.c == value) {
                return type;
            }
        }
        throw new InvalidDirectionException();
    }

    static boolean validDirection(char value) {
        for (Direction type : values()) {
            if (type.c == value) {
                return true;
            }
        }
        return false;
    }

    Pair<Integer, Integer> nextCord(Pair<Integer, Integer> pair) {
        return next.apply(pair);
    }
}
