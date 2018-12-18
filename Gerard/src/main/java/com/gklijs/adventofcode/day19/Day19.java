package com.gklijs.adventofcode.day19;

import io.reactivex.Observable;
import io.reactivex.Single;

public class Day19 {

    private Day19() {
        //prevent instantiation
    }

    public static Single<String> first(Observable<String> input) {
        return input
            .lastOrError();
    }

    public static Single<String> second(Observable<String> input) {
        return input
            .lastOrError();
    }
}
