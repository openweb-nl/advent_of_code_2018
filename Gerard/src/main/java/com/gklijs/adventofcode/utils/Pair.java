package com.gklijs.adventofcode.utils;

import java.util.function.Function;

public class Pair<T, U> {

    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public void changeFirst(Function<T, T> function) {
        this.first = function.apply(first);
    }

    public void changeSecond(Function<U, U> function) {
        this.second = function.apply(second);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object instanceof Pair) {
            Pair other = (Pair) object;
            return this.getFirst().equals(other.first) && this.getSecond().equals(other.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}
