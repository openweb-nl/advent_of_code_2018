package com.gklijs.adventofcode.day15;

class Elf extends Creature {

    private final int power;

    Elf(final int x, final int y) {
        this(x, y, 3);
    }

    Elf(final int x, final int y, final int power) {
        super(x, y);
        this.power = power;
    }

    @Override
    char getTarget() {
        return 'G';
    }

    @Override
    int getPower() {
        return power;
    }
}
