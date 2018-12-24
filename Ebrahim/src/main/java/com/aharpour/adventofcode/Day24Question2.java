package com.aharpour.adventofcode;

import lombok.SneakyThrows;

public class Day24Question2 {

    @SneakyThrows
    public static int calculate(String input)  {
        int boot = 0;

        while (!simulate(input, boot)) {
            boot++;
        }
        Day24Question1 simulation = new Day24Question1(input, false);
        simulation.setBoost(boot);
        return simulation.calculate();
    }

    private static boolean simulate(String input, int boost) {
        try {
            Day24Question1 simulation = new Day24Question1(input, false);
            simulation.setBoost(boost);
            simulation.calculate();
            return simulation.hasImmuneSystemWon();
        } catch (Day24Question1.StalemateException e) {
            return false;
        }
    }
}
