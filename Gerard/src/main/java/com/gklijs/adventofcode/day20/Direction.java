package com.gklijs.adventofcode.day20;

import java.util.function.UnaryOperator;

import com.gklijs.adventofcode.errors.InvalidInputException;
import com.gklijs.adventofcode.utils.Pair;

enum Direction {
    UP('N', pair -> pair.changeSecond(y -> y - 1)),
    DOWN('S', pair -> pair.changeSecond(y -> y + 1)),
    LEFT('W', pair -> pair.changeFirst(x -> x - 1)),
    RIGHT('E', pair -> pair.changeFirst(x -> x + 1));

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
        throw new InvalidInputException("char " + value + " is no valid value for a direction");
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
        Pair<Integer, Integer> result = new Pair<>(pair.getFirst(), pair.getSecond());
        return next.apply(result);
    }

    static Direction opposite(Direction direction) {
        switch (direction) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            default:
                throw new InvalidInputException("should not be possible");
        }
    }
}
