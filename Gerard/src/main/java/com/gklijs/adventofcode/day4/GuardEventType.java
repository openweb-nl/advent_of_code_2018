package com.gklijs.adventofcode.day4;

import java.util.List;
import java.util.function.BiFunction;

enum GuardEventType {
    BEGINS_SHIFT('G', GuardUpdates::beginsShift),
    WAKES_UP('w', GuardUpdates::wakesUp),
    FALLS_ASLEEP('f', GuardUpdates::fallsAsleep);

    final char start;
    final BiFunction<GuardEvent, GuardEvent, List<Integer>> update;

    GuardEventType(final char start, final BiFunction<GuardEvent, GuardEvent, List<Integer>> biFunction) {
        this.start = start;
        this.update = biFunction;
    }

    static GuardEventType get(char start) {
        for (GuardEventType type : values()) {
            if (type.start == start) {
                return type;
            }
        }
        throw new InvalidFirstCharException();
    }

    List<Integer> getAsleep(GuardEvent old, GuardEvent now) {
        return update.apply(old, now);
    }
}
