package com.aharpour.adventofcode;

public class Day17Question2 extends Day17 {

    public Day17Question2(String map) {
        super(500, map, false);
    }

    public int calculate() {
        simulateWaterFlow();
        return water.size();
    }
}