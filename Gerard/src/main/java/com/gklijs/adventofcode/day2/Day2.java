package com.gklijs.adventofcode.day2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.gklijs.adventofcode.Utils;
import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day2 {

    private Day2(){
        //prevent instantiation
    }

    public static Single<Integer> checksum(Observable<String> ids) {
        return ids
            .map(String::toCharArray)
            .map(Day2::toFrequencyMap)
            .map(Day2::hasTwoHasThree)
            .reduce(new Pair<>(0,0), Day2::reduce)
            .map(pair -> pair.getFirst() * pair.getSecond());
    }


    public static Single<String> commonLetters(Observable<String> ids) {
        return ids
            .flatMap(Day2::allExcludes)
            .scan(new Pair<Set<String>, String>(new HashSet<>(),null), Utils::firstDuplicate)
            .takeUntil(pair -> pair.getSecond() != null)
            .lastOrError()
            .map(Pair::getSecond);
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
        if(input.getFirst()) {
            result.changeFirst(x -> x + 1);
        }
        if(input.getSecond()) {
            result.changeSecond(x -> x + 1);
        }
        return result;
    }

    private static Observable<String> allExcludes(String input){
        return Observable.range(0, input.length())
            .map(i -> input.substring(0, i) + input.substring(i + 1));
    }
}
