package com.gklijs.adventofcode.day4;

enum GuardEventType {
    BEGINS_SHIFT('G'),
    WAKES_UP('w'),
    FALLS_ASLEEP('f');

    final char start;

    GuardEventType(final char start) {
        this.start = start;
    }

    static GuardEventType get(char start) {
        for (GuardEventType type : values()) {
            if (type.start == start) {
                return type;
            }
        }
        throw new InvalidFirstCharException();
    }
}
