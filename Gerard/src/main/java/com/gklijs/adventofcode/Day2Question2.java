package com.gklijs.adventofcode;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

class Day2Question2 {

    private Day2Question2(){
        //prevent instantiation
    }

    static Single<String> commonLetters(Observable<String> ids) {
        return ids
            .map(String::toCharArray)
            .scan(new Pair<>(new ArrayList<>(),null), Day2Question2::reduce)
            .takeUntil(pair -> pair.second != null)
            .lastOrError()
            .map(pair -> pair.second);
    }

    private static Pair<ArrayList<char[]>, String> reduce(Pair<ArrayList<char[]>, String> result, char[] input){
        for(char[] id : result.first){
            int diff = diffOnlyAt(id,input);
            if(diff != -1){
                result.second = exclude(input, diff);
                return result;
            }
        }
        result.first.add(input);
        return result;
    }

    private static String exclude(char[] input, int excluded){
        char [] result = new char[input.length -1];
        for(int i = 0; i < input.length ; i++){
            if(i == excluded){
                continue;
            }
            if(excluded > i){
                result[i] = input[i];
            }else{
                result[i - 1] = input[i];
            }
        }
        return new String(result);
    }

    private static int diffOnlyAt(char[] first, char[] second){
        int result = -1;
        if(first.length != second.length) return result;
        for(int i = 0; i < first.length ; i++){
            if(first[i] != second[i]){
                if(result == -1){
                    result = i;
                }else{
                    return -1;
                }
            }
        }
        return result;
    }
}
