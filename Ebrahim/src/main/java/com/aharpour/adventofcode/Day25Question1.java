package com.aharpour.adventofcode;

public class Day25Question1 extends Day25 {

    public Day25Question1(String input) {
        super(input);
    }

    public int calculate() {
        calculateConstellations();
        return constellations.size();
    }
}
