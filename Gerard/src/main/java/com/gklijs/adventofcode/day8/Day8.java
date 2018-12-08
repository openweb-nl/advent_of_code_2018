package com.gklijs.adventofcode.day8;

import java.util.Arrays;
import java.util.List;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day8 {

    private Day8(){
        //prevent instantiation
    }

    public static Single<Integer> allMetaData(Observable<String> input) {
        return buildTree(input)
            .map(Day8::totalMeta);
    }

    public static Single<Integer> getValue(Observable<String> input) {
        return buildTree(input)
            .map(Day8::getValue);
    }

    private static Single<Node> buildTree(Observable<String> input){
        return input
            .lastOrError()
            .map(Day8::split)
            .flattenAsObservable(i -> i)
            .map(Integer::parseInt)
            .repeat()
            .scan(new Pair<>(-2, null), Day8::reduce)
            .takeUntil(r -> r.getFirst() == -1)
            .lastOrError()
            .map(Pair::getSecond);
    }

    private static List<String> split(String input){
        return Arrays.asList(input.split(" "));
    }

    private static Pair<Integer, Node> reduce(Pair<Integer, Node> result, Integer input){
        if(result.getFirst() == -2){
            result.setFirst(input);
        }else if(result.getSecond() == null){
            result.setSecond(new Node(result.getFirst(), input));
        }else{
            boolean added = result.getSecond().add(input);
            if(!added){
                result.setFirst(-1);
            }
        }
        return result;
    }

    private static int totalMeta(Node node){
        int value = 0;
        for(Integer i : node.metaData){
            value += i;
        }
        for(Node n : node.childNodes){
            value += totalMeta(n);
        }
        return value;
    }

    private static int getValue(Node node){
        int value = 0;
        if(node.childNodes.isEmpty()){
            for(Integer i : node.metaData){
                value += i;
            }
        }else{
            for(Integer i : node.metaData){
                if(i == 0 || i > node.childCount) continue;
                value += getValue(node.childNodes.get(i - 1));
            }
        }
        return value;
    }
}
