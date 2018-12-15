package com.gklijs.adventofcode.day15;

class Goblin extends Creature {

    Goblin(final int x, final int y) {
        super(x, y);
    }

    @Override
    char getTarget() {
        return 'E';
    }

    @Override
    int getPower() {
        return 3;
    }
}