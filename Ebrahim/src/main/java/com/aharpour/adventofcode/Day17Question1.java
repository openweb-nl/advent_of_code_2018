package com.aharpour.adventofcode;

public class Day17Question1 extends Day17 {

    public Day17Question1(String map) {
        super(500, map, false);
    }

    public int calculate() {
        simulateWaterFlow();
        return water.size() + wetSand.size();
    }
}