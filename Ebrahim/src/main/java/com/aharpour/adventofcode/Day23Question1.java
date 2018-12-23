package com.aharpour.adventofcode;

public class Day23Question1 extends Day23 {

    public Day23Question1(String input) {
        super(input);
    }

    public long calculate() {
        Day23.NanoBot nanoBot = nanoBots.get(strongestIndex);
        return nanoBots.stream()
                .mapToInt(nanoBot::manhattanDistance)
                .filter(d -> d <= nanoBot.getRadious())
                .count();

    }
}
