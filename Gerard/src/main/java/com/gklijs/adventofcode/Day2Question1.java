package com.gklijs.adventofcode;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;

class Day2Question1 {

    private Day2Question1(){
        //prevent instantiation
    }

    static Single<Integer> checksum(Observable<String> ids) {
        return ids
            .map(String::toCharArray)
            .map(Day2Question1::toFrequencyMap)
            .map(Day2Question1::hasTwoHasThree)
            .reduce(new Pair<>(0,0), Day2Question1::reduce)
            .map(pair -> pair.first * pair.second);
    }

    private static Map<Character, Integer> toFrequencyMap(char[] chars){
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for(Character c: chars){
            if(frequencyMap.containsKey(c)){
                frequencyMap.put(c, frequencyMap.get(c)+1);
            }else{
                frequencyMap.put(c, 1);
            }
        }
        return frequencyMap;
    }

    private static Pair<Boolean, Boolean> hasTwoHasThree(Map<Character, Integer> frequencyMap){
        return new Pair<>(frequencyMap.containsValue(2), frequencyMap.containsValue(3));
    }

    private static Pair<Integer, Integer> reduce(Pair<Integer, Integer> result, Pair<Boolean, Boolean> input){
        if(input.first) {
            result.first++;
        }
        if(input.second) {
            result.second++;
        }
        return result;
    }
}
