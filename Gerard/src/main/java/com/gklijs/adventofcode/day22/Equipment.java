package com.gklijs.adventofcode.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public enum Equipment {
    CLIMBING_GEAR(x -> x != 2),
    TORCH(x -> x != 1),
    NONE(x -> x != 0);

    private Function<Integer, Boolean> validation;

    Equipment(final Function<Integer, Boolean> validation) {
        this.validation = validation;
    }

    List<Equipment> others() {
        List<Equipment> l = new ArrayList<>();
        for (Equipment equipment : Equipment.values()) {
            if (this != equipment) {
                l.add(equipment);
            }
        }
        return l;
    }

    boolean isValid(int target) {
        return validation.apply(target);
    }
}
