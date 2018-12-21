package com.aharpour.adventofcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ebrahim Aharpour
 * @since 12/21/2018
 */
public class Day21Question1 extends Day19 {
    public Day21Question1(String input) {
        super(input);
    }

    private Set<Integer> values = new HashSet<>();

    public int calculate() {
        setRegisters(new int[]{0, 0, 0, 0, 0, 0});
        run();
        return getRegisters()[0];
    }

    @Override
    public void applyOptimization(int instructionIndex) {
        if (instructionIndex == 29) {
            int value = getRegisters()[4];
            if (!values.contains(value)) {
                values.add(value);
                out.println("NewValue=" + value);
            }
            printResiters();
            printInstruction(instructionIndex);
        }
    }
}
