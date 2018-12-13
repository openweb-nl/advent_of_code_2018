package com.gklijs.adventofcode.day13;

import java.util.function.UnaryOperator;

enum Turn {
    LEFT(direction -> {
        if (direction == Direction.UP) {
            return Direction.LEFT;
        }
        if (direction == Direction.DOWN) {
            return Direction.RIGHT;
        }
        if (direction == Direction.LEFT) {
            return Direction.DOWN;
        }
        return Direction.UP;
    }),
    STRAIGHT(direction -> direction),
    RIGHT(direction -> {
        if (direction == Direction.UP) {
            return Direction.RIGHT;
        }
        if (direction == Direction.DOWN) {
            return Direction.LEFT;
        }
        if (direction == Direction.LEFT) {
            return Direction.UP;
        }
        return Direction.DOWN;
    });

    private final UnaryOperator<Direction> nextDirection;

    Direction nextDirection(Direction direction) {
        return nextDirection.apply(direction);
    }

    Turn(final UnaryOperator<Direction> nextDirection) {
        this.nextDirection = nextDirection;
    }

    final Turn nextTurn() {
        if (this == LEFT) {
            return STRAIGHT;
        }
        if (this == STRAIGHT) {
            return RIGHT;
        } else {
            return LEFT;
        }
    }
}
