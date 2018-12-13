package com.gklijs.adventofcode.day13;

import java.util.List;

import com.gklijs.adventofcode.utils.Pair;

class MineCart implements Comparable<MineCart> {

    private Pair<Integer, Integer> cord;
    private Direction direction;
    private int stepsTaken;
    private Turn turn;

    MineCart(int x, int y, char d) {
        turn = Turn.LEFT;
        cord = new Pair<>(x, y);
        direction = Direction.get(d);
    }

    void move(List<char[]> tracks) {
        cord = direction.nextCord(cord);
        stepsTaken++;
        char trackPiece = tracks.get(cord.getSecond())[cord.getFirst()];
        if (trackPiece == '+') {
            direction = turn.nextDirection(direction);
            turn = turn.nextTurn();
        } else if (trackPiece == '/') {
            if (direction == Direction.RIGHT) {
                direction = Direction.UP;
            } else if (direction == Direction.DOWN) {
                direction = Direction.LEFT;
            } else if (direction == Direction.UP) {
                direction = Direction.RIGHT;
            } else {
                direction = Direction.DOWN;
            }
        } else if (trackPiece == '\\') {
            if (direction == Direction.RIGHT) {
                direction = Direction.DOWN;
            } else if (direction == Direction.DOWN) {
                direction = Direction.RIGHT;
            } else if (direction == Direction.LEFT) {
                direction = Direction.UP;
            } else {
                direction = Direction.LEFT;
            }
        }
    }

    int stepsTaken() {
        return stepsTaken;
    }

    Pair<Integer, Integer> getCord() {
        return cord;
    }

    @Override
    public int compareTo(final MineCart o) {
        if (this.stepsTaken != o.stepsTaken) {
            return this.stepsTaken - o.stepsTaken;
        }
        if (!this.getCord().getSecond().equals(o.getCord().getSecond())) {
            return this.getCord().getSecond() - o.getCord().getSecond();
        }
        return this.getCord().getFirst() - o.getCord().getFirst();
    }
}
