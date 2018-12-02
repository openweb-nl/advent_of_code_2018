package com.gklijs.adventofcode;

import io.reactivex.Observable;
import io.reactivex.Single;

class Day1Question1 {

    private Day1Question1(){
        //prevent instantiation
    }

    static Single<Integer> calculateFrequency(Observable<String> frequencyChanges) {
        return frequencyChanges
            .map(Integer::valueOf)
            .reduce(0, (frequency , frequencyChange) -> frequency + frequencyChange);
    }
}
