package com.aharpour.adventofcode;

import java.util.Arrays;
import java.util.List;

public abstract class Day14 {

    private static final List<Integer> InitialValues = Arrays.asList(3, 7, 1, 0);
    protected final List<Integer> recipes;
    private int currentPosition1 = 0;
    private int currentPosition2 = 1;

    protected Day14(List<Integer> recipes) {
        this.recipes = recipes;
        recipes.addAll(InitialValues);
    }


    protected void nextStep() {
        int r1 = recipes.get(currentPosition1);
        int r2 = recipes.get(currentPosition2);
        int sum = r1 + r2;
        if (sum > 9) {
            recipes.add(sum / 10);
        }
        recipes.add(sum % 10);
        moveToNewRecipes();
    }

    private void moveToNewRecipes() {
        int size = recipes.size();
        int r1 = recipes.get(currentPosition1);
        int r2 = recipes.get(currentPosition2);
        currentPosition1 = (r1 + 1 + currentPosition1) % size;
        currentPosition2 = (r2 + 1 + currentPosition2) % size;
    }
}
