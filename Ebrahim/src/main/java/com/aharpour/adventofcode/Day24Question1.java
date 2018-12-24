package com.aharpour.adventofcode;

import java.util.List;

public class Day24Question1 extends Day24 {

    public Day24Question1(String input, boolean print) {
        super(input, print);
    }

    public int calculate() throws StalemateException {
        int sum = getSum();
        print();
        while (getNumberOfLiving(this.infection) > 0 && getNumberOfLiving(this.immuneSystem) > 0) {
            fight();
            print();
            int newSum = getSum();
            if (newSum == sum) {
                throw new StalemateException();
            } else {
                sum = newSum;
            }
        }
        return sum;
    }

    private int getSum() {
        return groups.stream().filter(group -> group.getUnits() > 0).mapToInt(Group::getUnits).sum();
    }

    public boolean hasImmuneSystemWon() {
        return getNumberOfLiving(this.infection) == 0 && getNumberOfLiving(this.immuneSystem) > 0;
    }

    private long getNumberOfLiving(List<Group> gro) {
        return gro.stream().filter(group -> group.getUnits() > 0).count();
    }

    public class StalemateException extends Exception {
    }
}
