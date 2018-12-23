package com.aharpour.adventofcode;


import com.aharpour.adventofcode.utils.IntPair;

public class Day15Question1 extends Day15 {

    private final int originalNumberOfElves;
    private boolean print;
    private boolean tolerateDeadElevs = true;


    public Day15Question1(String map, boolean print, int boost) {
        super(200, 3, map, boost);
        originalNumberOfElves = elves.size();
        this.print = print;
    }


    public IntPair calculate() {
        int r = 0;
        try {
            while (true) {
                round();
                if (!tolerateDeadElevs && originalNumberOfElves != elves.size()) {
                    throw new DeadElfException();
                }
                r++;
                if (print) {
                    System.out.println("Round: " + r);
                    print();
                }
            }
        } catch (NoMoreEnemiesException e) {
            removeDeadOnes();
            if (!tolerateDeadElevs && originalNumberOfElves != elves.size()) {
                throw new DeadElfException();
            }
        }
        int sum = creatures.stream()
                .filter(c -> !c.isDead())
                .mapToInt(Creature::getHitPoint).sum();
        if (print) {
            System.out.println(goblins);
            System.out.println(elves);
        }
        return new IntPair(sum, r);
    }

    public void setTolerateDeadElevs(boolean tolerateDeadElevs) {
        this.tolerateDeadElevs = tolerateDeadElevs;
    }

    public class DeadElfException extends RuntimeException {
    }
}