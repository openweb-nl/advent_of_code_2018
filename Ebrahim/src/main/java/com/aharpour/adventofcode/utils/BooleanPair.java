package com.aharpour.adventofcode.utils;

public class BooleanPair {

    private final boolean key;
    private final boolean value;

    public BooleanPair(boolean key, boolean value) {
        this.key = key;
        this.value = value;
    }

    public boolean getKey() {
        return key;
    }

    public boolean getValue() {
        return value;
    }

    public BooleanPair combine(BooleanPair another) {
        return new BooleanPair(this.key || another.getKey(), this.value || another.getValue());
    }

    public IntPair toIntPair() {
        return new IntPair(this.key ? 1 : 0, this.value ? 1 : 0);
    }

    @Override
    public String toString() {
        return "BooleanPair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
