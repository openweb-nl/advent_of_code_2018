package com.gklijs.adventofcode.day1;

import java.util.HashSet;
import java.util.Set;

import com.gklijs.adventofcode.Utils;
import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day1 {

    private Day1(){
        //prevent instantiation
    }

    public static Single<Integer> calculateFrequency(Observable<String> frequencyChanges) {
        return frequencyChanges
            .map(Integer::valueOf)
            .reduce(0, (frequency , frequencyChange) -> frequency + frequencyChange);
    }

    public static Single<Integer> firstDoubleFrequency(Observable<String> frequencyChanges) {
        return frequencyChanges
            .repeat()
            .map(Integer::valueOf)
            .scan(0, (frequency , frequencyChange) -> frequency + frequencyChange)
            .scan(new Pair<Set<Integer>, Integer>(new HashSet<>(), null), Utils::firstDuplicate)
            .takeUntil(pair -> pair.getSecond() != null)
            .lastOrError()
            .map(Pair::getSecond);
    }
}
