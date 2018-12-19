package com.aharpour.adventofcode;

public class Day19Question2 extends Day19 {

    public Day19Question2(String input) {
        super(input);
    }

    public int calculate() {
        setRegisters(new int[]{1, 0, 0, 0, 0, 0});
        run();
        return getRegisters()[0];
    }

    @Override
    public void applyOptimization(int instructionIndex) {
        // What a cheat. I am ashamed of this hack but it works
        int[] registers = getRegisters();
        if (instructionIndex == 5) {
            int limit;
            if (registers[3] != 0 && registers[5] % registers[3] == 0) {
                limit = registers[5] / registers[3];
            } else {
                limit = registers[5];
            }
            limit--;
            if (registers[1] < limit) {
                registers[1] = limit;
            }
        }
    }
}
