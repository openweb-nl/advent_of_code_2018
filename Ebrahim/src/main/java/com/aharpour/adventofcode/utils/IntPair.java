package com.aharpour.adventofcode.utils;

public class IntPair {
    private final int key;
    private final int value;

    public IntPair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public IntPair combine(IntPair another) {
        return new IntPair(this.getKey() + another.getKey(), this.getValue() + another.getValue());
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}