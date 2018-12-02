package com.aharpour.adventofcode.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SimpleArrayBiReducer<T, M> {

    private final ArrayBiReducer<T, M, List<M>, List<M>> ar;

    private SimpleArrayBiReducer(ArrayBiReducer<T, M, List<M>, List<M>> ar) {
        this.ar = ar;
    }

    public List<M> operate(T[] array) {
        return ar.operate(array);
    }


    public static <T, M> Builder<T, M> builder() {
        return new Builder<>();
    }

    public static final class Builder<T, M> {
        private boolean reflective = true;
        private boolean includeSelf = false;
        private boolean applyReverseIfEqual = false;
        private BiFunction<T, T, M> operator;

        private Builder() {
        }


        public Builder<T, M> reflective(boolean reflective) {
            this.reflective = reflective;
            return this;
        }

        public Builder<T, M> includeSelf(boolean includeSelf) {
            this.includeSelf = includeSelf;
            return this;
        }

        public Builder<T, M> applyReverseIfEqual(boolean applyReverseIfEqual) {
            this.applyReverseIfEqual = applyReverseIfEqual;
            return this;
        }

        public Builder<T, M> operator(BiFunction<T, T, M> operator) {
            this.operator = operator;
            return this;
        }

        public SimpleArrayBiReducer<T, M> build() {
            return new SimpleArrayBiReducer<>(ArrayBiReducer.<T, M, List<M>, List<M>>builder().supplier(ArrayList::new)
                    .accumulator(List::add)
                    .finisher(Function.identity())
                    .operator(operator)
                    .reflective(reflective)
                    .includeSelf(includeSelf)
                    .applyReverseIfEqual(applyReverseIfEqual).build());
        }
    }
}
