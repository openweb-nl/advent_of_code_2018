package com.aharpour.adventofcode.utils;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayBiReducer<T, M, A, R> {

    private ArrayBiReducer() {
    }

    private boolean commutative;
    private boolean includeSelf;
    private boolean applyReverseIfEqual;
    private Supplier<A> supplier;
    private BiFunction<T, T, M> operator;
    private BiConsumer<A, M> accumulator;
    private Function<A, R> finisher;

    public R operate(T[] array) {
        A accumulation = supplier.get();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + (includeSelf ? 0 : 1); j < array.length; j++) {
                T first = array[i];
                T second = array[j];
                apply(first, second, accumulation);
                if (!commutative && i != j && (applyReverseIfEqual || !first.equals(second))) {
                    apply(second, first, accumulation);
                }
            }
        }
        return finisher.apply(accumulation);
    }

    private void apply(T first, T second, A accumulation) {
        M result = operator.apply(first, second);
        if (result != null) {
            accumulator.accept(accumulation, result);
        }
    }

    public static <T, M, A, R> Builder<T, M, A, R> builder() {
        return new Builder<>();
    }

    public static class Builder<T, M, A, R> {
        private boolean commutative = true;
        private boolean includeSelf = false;
        private boolean applyReverseIfEqual = false;
        private Supplier<A> supplier;
        private BiFunction<T, T, M> operator;
        private BiConsumer<A, M> accumulator;
        private Function<A, R> finisher;

        private Builder() {
        }

        public Builder<T, M, A, R> commutative(boolean commutative) {
            this.commutative = commutative;
            return this;
        }

        public Builder<T, M, A, R> includeSelf(boolean includeSelf) {
            this.includeSelf = includeSelf;
            return this;
        }

        public Builder<T, M, A, R> applyReverseIfEqual(boolean applyReverseIfEqual) {
            this.applyReverseIfEqual = applyReverseIfEqual;
            return this;
        }

        public Builder<T, M, A, R> supplier(Supplier<A> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder<T, M, A, R> operator(BiFunction<T, T, M> operator) {
            this.operator = operator;
            return this;
        }

        public Builder<T, M, A, R> accumulator(BiConsumer<A, M> accumulator) {
            this.accumulator = accumulator;
            return this;
        }

        public Builder<T, M, A, R> finisher(Function<A, R> finisher) {
            this.finisher = finisher;
            return this;
        }

        public ArrayBiReducer<T, M, A, R> build() {
            ArrayBiReducer<T, M, A, R> arrayBiReducer = new ArrayBiReducer<>();
            if (this.operator == null || this.supplier == null && this.accumulator == null && this.finisher == null) {
                throw new IllegalArgumentException();
            }
            arrayBiReducer.operator = this.operator;
            arrayBiReducer.supplier = this.supplier;
            arrayBiReducer.accumulator = this.accumulator;
            arrayBiReducer.finisher = this.finisher;
            arrayBiReducer.includeSelf = this.includeSelf;
            arrayBiReducer.applyReverseIfEqual = this.applyReverseIfEqual;
            arrayBiReducer.commutative = this.commutative;
            return arrayBiReducer;
        }
    }


}
