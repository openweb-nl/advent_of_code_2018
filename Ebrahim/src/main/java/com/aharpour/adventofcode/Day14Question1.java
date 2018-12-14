package com.aharpour.adventofcode;


import java.util.ArrayList;

public class Day14Question1 extends Day14 {

    private static final int NUMBER_OF_RECIPES_TO_READ = 10;
    private final int numberOfRecipesToSkip;

    public Day14Question1(int numberOfRecipesToSkip) {
        super(new ArrayList<>(numberOfRecipesToSkip + NUMBER_OF_RECIPES_TO_READ + 2));
        this.numberOfRecipesToSkip = numberOfRecipesToSkip;
    }

    public String calculate() {
        while (recipes.size() < numberOfRecipesToSkip + NUMBER_OF_RECIPES_TO_READ) {
            nextStep();
        }
        return getOutput();
    }

    private String getOutput() {
        StringBuilder builder = new StringBuilder();
        int to = numberOfRecipesToSkip + NUMBER_OF_RECIPES_TO_READ;
        for (int i = numberOfRecipesToSkip; i < to; i++) {
            builder.append(recipes.get(i));
        }
        return builder.toString();
    }


}