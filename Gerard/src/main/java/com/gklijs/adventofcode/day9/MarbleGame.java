package com.gklijs.adventofcode.day9;

class MarbleGame {

    private final int[] nextMarble;
    private final long[] scores;
    private final int[] history;
    private int currentMarble;
    private int turnCounter;
    private int playerCounter;

    MarbleGame(int players, int marbles) {
        nextMarble = new int[marbles + 1];
        scores = new long[players];
        history = new int[2];
    }

    void play(int value) {
        turnCounter++;
        playerCounter++;
        if (playerCounter == scores.length) {
            playerCounter = 0;
        }
        if (turnCounter == 23) {
            turnCounter = 0;
            int removedMarble = history[1];
            scores[playerCounter] += removedMarble;
            scores[playerCounter] += value;
            nextMarble[history[0]] = nextMarble[history[1]];
            currentMarble = nextMarble[history[1]];
        } else {
            currentMarble = nextMarble[currentMarble];
            if (turnCounter == 19) {
                history[1] = currentMarble;
            }
            nextMarble[value] = nextMarble[currentMarble];
            nextMarble[currentMarble] = value;
            currentMarble = value;
            if (turnCounter == 18) {
                history[0] = currentMarble;
            }
        }
    }

    long[] getScores() {
        return scores;
    }
}
