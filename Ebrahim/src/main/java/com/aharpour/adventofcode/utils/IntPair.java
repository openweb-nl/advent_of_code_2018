package com.aharpour.adventofcode.utils;

public class IntPair {
    private int key;
    private int value;

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

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void incrementValue() {
        this.value++;
    }

    public void incrementKey() {
        this.key++;
    }

    @Override
    public String toString() {
        return "IntPair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}