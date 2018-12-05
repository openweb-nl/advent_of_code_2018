package com.gklijs.adventofcode.day4;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GuardUpdates {

    private GuardUpdates() {
        //prevent instantiation
    }

    static List<Integer> beginsShift(GuardEvent old, GuardEvent now) {
        if (old != null && old.guardEventType == GuardEventType.FALLS_ASLEEP) {
            return IntStream.range(old.minute, 60).boxed().collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    static List<Integer> wakesUp(GuardEvent old, GuardEvent now) {
        now.id = old.id;
        if (old.guardEventType == GuardEventType.FALLS_ASLEEP) {
            return IntStream.range(old.minute, now.minute).boxed().collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    static List<Integer> fallsAsleep(GuardEvent old, GuardEvent now) {
        now.id = old.id;
        return Collections.emptyList();
    }
}
