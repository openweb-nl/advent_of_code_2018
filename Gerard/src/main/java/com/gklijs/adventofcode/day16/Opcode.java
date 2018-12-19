package com.gklijs.adventofcode.day16;

import java.util.function.BiFunction;

enum Opcode {
    ADDR((a, b) -> a + b, false, false),
    ADDI((a, b) -> a + b, false, true),
    MULR((a, b) -> a * b, false, false),
    MULI((a, b) -> a * b, false, true),
    BANR((a, b) -> a & b, false, false),
    BANI((a, b) -> a & b, false, true),
    BONR((a, b) -> a | b, false, false),
    BONI((a, b) -> a | b, false, true),
    SETR((a, b) -> a, false, false),
    SETI((a, b) -> a, true, true),
    GTIR((a, b) -> a > b ? 1 : 0, true, false),
    GTRI((a, b) -> a > b ? 1 : 0, false, true),
    GTRR((a, b) -> a > b ? 1 : 0, false, false),
    EQIR((a, b) -> a.equals(b) ? 1 : 0, true, false),
    EQRI((a, b) -> a.equals(b) ? 1 : 0, false, true),
    EQRR((a, b) -> a.equals(b) ? 1 : 0, false, false);

    private BiFunction<Integer, Integer, Integer> function;
    private boolean directA;
    private boolean directB;

    Opcode(final BiFunction<Integer, Integer, Integer> function, final boolean directA, final boolean directB) {
        this.function = function;
        this.directA = directA;
        this.directB = directB;
    }

    int[] apply(int a, int b, int c, int[] register) {
        int[] result = register.clone();
        int aa = directA ? a : register[a];
        int bb = directB ? b : register[b];
        result[c] = function.apply(aa, bb);
        return result;
    }
}
