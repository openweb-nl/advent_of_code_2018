package com.gklijs.adventofcode.day18;

import java.util.function.Consumer;
import java.util.function.Function;

import com.gklijs.adventofcode.errors.InvalidInputException;
import com.gklijs.adventofcode.utils.Triple;

enum Acre {
    GROUND('.', c -> c.changeFirst(g -> g + 1), Acre::nextForGround),
    TREES('|', c -> c.changeSecond(t -> t + 1), Acre::nextForTrees),
    LUMBERYARD('#', c -> c.changeThird(l -> l + 1), Acre::nextForLumberyard);

    private char value;
    private Consumer<Triple<Integer, Integer, Integer>> update;
    private Function<Triple<Integer, Integer, Integer>, Acre> next;

    Acre(final char value,
         final Consumer<Triple<Integer, Integer, Integer>> update,
         final Function<Triple<Integer, Integer, Integer>, Acre> next) {
        this.value = value;
        this.update = update;
        this.next = next;
    }

    char getValue() {
        return value;
    }

    static Acre get(char value) {
        for (Acre acre : values()) {
            if (acre.value == value) {
                return acre;
            }
        }
        throw new InvalidInputException("char " + value + " is not a valid acre value");
    }

    Acre next(Triple<Integer, Integer, Integer> counter) {
        return next.apply(counter);
    }

    void update(Triple<Integer, Integer, Integer> counter) {
        update.accept(counter);
    }

    private static Acre nextForGround(Triple<Integer, Integer, Integer> counter) {
        if (counter.getSecond() >= 3) {
            return Acre.TREES;
        }
        return Acre.GROUND;
    }

    private static Acre nextForTrees(Triple<Integer, Integer, Integer> counter) {
        if (counter.getThird() >= 3) {
            return Acre.LUMBERYARD;
        }
        return Acre.TREES;
    }

    private static Acre nextForLumberyard(Triple<Integer, Integer, Integer> counter) {
        if (counter.getSecond() >= 1 && counter.getThird() >= 1) {
            return Acre.LUMBERYARD;
        }
        return Acre.GROUND;
    }
}
