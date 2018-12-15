package com.gklijs.adventofcode.day15;

import java.util.function.UnaryOperator;

import com.gklijs.adventofcode.utils.Pair;

enum Move {
    UP(pair -> pair.changeSecond(y -> y - 1)),
    LEFT(pair -> pair.changeFirst(x -> x - 1)),
    RIGHT(pair -> pair.changeFirst(x -> x + 1)),
    DOWN(pair -> pair.changeSecond(y -> y + 1)),
    NOT(pair -> pair);

    private final UnaryOperator<Pair<Integer, Integer>> next;

    Move(final UnaryOperator<Pair<Integer, Integer>> next) {
        this.next = next;
    }

    Pair<Integer, Integer> nextCord(Pair<Integer, Integer> pair) {
        return next.apply(new Pair<>(pair.getFirst(), pair.getSecond()));
    }

    static boolean skip(Move move, Move other) {
        switch (move) {
            case UP:
                return other == Move.DOWN;
            case LEFT:
                return other == Move.UP || other == Move.RIGHT;
            case RIGHT:
                return other == Move.UP || other == Move.LEFT;
            case DOWN:
                return other != Move.DOWN;
            default:
                return false;
        }
    }
}
