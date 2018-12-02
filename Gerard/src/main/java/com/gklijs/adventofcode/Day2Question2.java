package com.gklijs.adventofcode;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Single;

class Day2Question2 {

    private Day2Question2(){
        //prevent instantiation
    }

    static Single<String> commonLetters(Observable<String> ids) {
        return ids
            .flatMap(Day2Question2::allExcludes)
            .scan(new Pair<Set<String>, String>(new HashSet<>(),null), Day1Question2::firstDuplicate)
            .takeUntil(pair -> pair.second != null)
            .lastOrError()
            .map(pair -> pair.second);
    }

    private static Observable<String> allExcludes(String input){
        return Observable.range(0, input.length())
            .map(i -> input.substring(0, i) + input.substring(i + 1));
    }
}
