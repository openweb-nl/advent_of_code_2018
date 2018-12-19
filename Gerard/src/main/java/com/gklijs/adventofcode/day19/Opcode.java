package com.gklijs.adventofcode.day19;

import java.util.function.BiFunction;

import com.gklijs.adventofcode.errors.InvalidInputException;

enum Opcode {
    ADDR("addr", (a, b) -> a + b, false, false),
    ADDI("addi", (a, b) -> a + b, false, true),
    MULR("mulr", (a, b) -> a * b, false, false),
    MULI("muli", (a, b) -> a * b, false, true),
    BANR("banr", (a, b) -> a & b, false, false),
    BANI("bani", (a, b) -> a & b, false, true),
    BONR("bonr", (a, b) -> a | b, false, false),
    BONI("boni", (a, b) -> a | b, false, true),
    SETR("setr", (a, b) -> a, false, false),
    SETI("seti", (a, b) -> a, true, true),
    GTIR("gtir", (a, b) -> a > b ? 1 : 0, true, false),
    GTRI("gtri", (a, b) -> a > b ? 1 : 0, false, true),
    GTRR("gtrr", (a, b) -> a > b ? 1 : 0, false, false),
    EQIR("eqir", (a, b) -> a.equals(b) ? 1 : 0, true, false),
    EQRI("eqri", (a, b) -> a.equals(b) ? 1 : 0, false, true),
    EQRR("eqrr", (a, b) -> a.equals(b) ? 1 : 0, false, false);

    private String value;
    private BiFunction<Integer, Integer, Integer> function;
    private boolean directA;
    private boolean directB;

    Opcode(final String value, final BiFunction<Integer, Integer, Integer> function, final boolean directA, final boolean directB) {
        this.value = value;
        this.function = function;
        this.directA = directA;
        this.directB = directB;
    }

    void apply(int[] ops, int[] register) {
        int a = ops[0];
        int b = ops[1];
        int c = ops[2];
        int aa = directA ? a : register[a];
        int bb = directB ? b : register[b];
        register[c] = function.apply(aa, bb);
    }

    static Opcode get(String value) {
        for (Opcode opcode : Opcode.values()) {
            if (opcode.value.equals(value)) {
                return opcode;
            }
        }
        throw new InvalidInputException("Value " + value + " is not a valid value for an Opcode");
    }
}

