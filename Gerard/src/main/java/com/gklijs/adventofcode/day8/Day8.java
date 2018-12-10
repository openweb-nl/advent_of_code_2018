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
        int value = node.metaData.stream().mapToInt(Integer::intValue).sum();
        return value + node.childNodes.stream()
            .map(Day8::totalMeta)
            .mapToInt(Integer::intValue)
            .sum();
    }

    private static int getValue(Node node){
        if(node.childNodes.isEmpty()){
            return node.metaData.stream().mapToInt(Integer::intValue).sum();
        }else{
            return node.metaData.stream()
                .filter(x -> x == 0 || x <= node.childCount)
                .mapToInt(x -> getValue(node.childNodes.get(x - 1)))
                .sum();
        }
    }
}
