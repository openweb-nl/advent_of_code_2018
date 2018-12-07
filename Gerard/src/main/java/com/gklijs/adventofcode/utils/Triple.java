package com.gklijs.adventofcode.utils;

import java.util.function.UnaryOperator;

public class Triple<T, U, V> {

    private T first;
    private U second;
    private V third;

    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public void setThird(V third) {
        this.third = third;
    }

    public void changeFirst(UnaryOperator<T> operator) {
        this.first = operator.apply(first);
    }

    public void changeSecond(UnaryOperator<U> operator) {
        this.second = operator.apply(second);
    }

    public void changeThird(UnaryOperator<V> operator) {
        this.third = operator.apply(third);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object instanceof Triple) {
            Triple other = (Triple) object;
            return this.getFirst().equals(other.first) && this.getSecond().equals(other.second) && this.third.equals(other.third);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        return result;
    }
}
