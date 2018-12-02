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
            .scan(new Matcher(), Matcher::tryMatch)
            .takeUntil(matcher -> matcher.getLastDouble() != null)
            .last(new Matcher())
            .map(Matcher::getLastDouble);
    }

    private static class Matcher{
        private Set<Integer> pastFrequencies = new HashSet<>();
        private Integer lastDouble = null;

        Matcher tryMatch(int frequency){
            if(pastFrequencies.contains(frequency)){
                lastDouble = frequency;
            }else{
                pastFrequencies.add(frequency);
            }
            return this;
        }

        Integer getLastDouble(){
            return lastDouble;
        }
    }
}
