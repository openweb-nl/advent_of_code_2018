package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.IntPair;
import lombok.SneakyThrows;

public class Day15Question2 {


    @SneakyThrows
    public static int calculate(String input) {
        IntPair result;
        int boost = 0;

        while ((result = simulate(input, boost)) == null) {
            boost++;
            System.out.println(boost);
        }
        return result.getKey() * result.getValue();
    }

    private static IntPair simulate(String input, int boost) {
        try {
            Day15Question1 simulation = new Day15Question1(input, false, boost);
            simulation.setTolerateDeadElevs(false);
            return simulation.calculate();
        } catch (Day15Question1.DeadElfException e) {
            return null;
        }
    }
}