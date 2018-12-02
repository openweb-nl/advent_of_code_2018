package com.gklijs.adventofcode;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Single;

class Day1Question2 {

    private Day1Question2(){
        //prevent instantiation
    }

    static Single<Integer> firstDoubleFrequency(Observable<String> frequencyChanges) {
        return frequencyChanges
            .repeat()
            .map(Integer::valueOf)
            .scan(0, (frequency , frequencyChange) -> frequency + frequencyChange)
            .scan(new Pair<Set<Integer>, Integer>(new HashSet<>(), null), Day1Question2::tryMatch)
            .takeUntil(pair -> pair.second != null)
            .lastOrError()
            .map(pair -> pair.second);
    }

    private static Pair<Set<Integer>, Integer> tryMatch(Pair<Set<Integer>, Integer> pair, int currentFrequency){
        if(pair.first.contains(currentFrequency)){
            pair.second = currentFrequency;
        }else{
            pair.first.add(currentFrequency);
        }
        return pair;
    }
}
