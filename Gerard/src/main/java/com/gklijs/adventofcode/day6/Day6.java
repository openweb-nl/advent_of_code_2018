package com.gklijs.adventofcode.day6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

import com.gklijs.adventofcode.Utils;
import com.gklijs.adventofcode.utils.Pair;
import com.gklijs.adventofcode.utils.Triple;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day6 {

    private Day6(){
        //prevent instantiation
    }

    public static Single<Integer> largestFiniteArea(Observable<String> coords) {
        return coords
            .map(Day6::getCord)
            .reduce(new Pair<>(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, new HashSet<>()), Day6::reduce)
            .map(p -> new Triple<>(p.getFirst(), p.getSecond(), atBorder(p.getFirst(), p.getSecond())))
            .map(Day6::toFrequencyMap)
            .map(Map::values)
            .flattenAsObservable(i -> i)
            .reduce(0 , (o, n) -> n > o ? n : o);
    }

    public static Single<Integer> toAllLessThen(Observable<String> coords, int threshHold) {
        return coords
            .map(Day6::getCord)
            .reduce(new Pair<>(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, new HashSet<>()), Day6::reduce)
            .map(Day6::toDistances)
            .flattenAsObservable(s -> s)
            .filter(x -> x[2] < threshHold)
            .count()
            .map(Long::intValue);
    }

    private static Triple<Integer,Integer, UUID> getCord(String value){
        int seperator = value.indexOf(',');
        return new Triple<>(Integer.parseInt(value.substring(0, seperator)), Integer.parseInt(value.substring(seperator + 2)), UUID.randomUUID());
    }

    private static Pair<int[], Set<Triple<Integer,Integer, UUID>>> reduce(Pair<int[], Set<Triple<Integer,Integer, UUID>>> result, Triple<Integer,Integer, UUID> coord){
        int[] mm = result.getFirst();
        mm[0] = Math.min(coord.getFirst(), mm[0]);
        mm[1] = Math.min(coord.getSecond(), mm[1]);
        mm[2] = Math.max(coord.getFirst(), mm[2]);
        mm[3] = Math.max(coord.getSecond(), mm[3]);
        result.getSecond().add(coord);
        return result;
    }

    private static void updateNearest(Pair<Integer, UUID> result, Triple<Integer,Integer, UUID> update, int x, int y){
        int distance = Math.abs(x - update.getFirst()) +  Math.abs(y - update.getSecond());
        if(distance == result.getFirst()){
            result.setSecond(null);
        }else if(distance < result.getFirst()){
            result.setFirst(distance);
            result.setSecond(update.getThird());
        }
    }

    private static UUID nearestNeighbour (int x, int y, Set<Triple<Integer,Integer, UUID>> coords){
        Pair<Integer, UUID> result = new Pair<>(Integer.MAX_VALUE, null);
        for(Triple<Integer,Integer, UUID> coord : coords){
            updateNearest(result, coord, x, y);
        }
        return result.getSecond();
    }

    private static Set<UUID> atBorder(int[] mm, Set<Triple<Integer,Integer, UUID>> coords){
        Set<UUID> borderCoords = new HashSet<>();
        borderCoords.add(null);
        IntStream.range(mm[0] + 1, mm[2]).forEach(x -> borderCoords.add(nearestNeighbour(x, mm[1], coords)));
        IntStream.range(mm[0] + 1, mm[2]).forEach(x -> borderCoords.add(nearestNeighbour(x, mm[3], coords)));
        IntStream.range(mm[1] + 1, mm[3]).forEach(y -> borderCoords.add(nearestNeighbour(mm[0], y, coords)));
        IntStream.range(mm[1] + 1, mm[3]).forEach(y -> borderCoords.add(nearestNeighbour(mm[2], y, coords)));
        return borderCoords;
    }

    private static Map<UUID, Integer> toFrequencyMap(Triple<int[], Set<Triple<Integer,Integer, UUID>>, Set<UUID>> t){
        Map<UUID, Integer> frequencyMap = new HashMap<>();
        int[] mm = t.getFirst();
        for(int x = mm[0] + 1; x < mm[2] ;x++){
            for(int y = mm[1] + 1; y < mm[3] ;y++){
                UUID nearest = nearestNeighbour(x,y,t.getSecond());
                if(! t.getThird().contains(nearest)){
                    Utils.addToFrequencyMap(frequencyMap, nearest);
                }
            }
        }
        return frequencyMap;
    }

    private static int allDistances (int x, int y, Set<Triple<Integer,Integer, UUID>> coords){
        int allDistances = 0;
        for(Triple<Integer,Integer, UUID> coord : coords){
            allDistances += Math.abs(x - coord.getFirst()) +  Math.abs(y - coord.getSecond());
        }
        return allDistances;
    }

    private static Set<int[]> toDistances(Pair<int[], Set<Triple<Integer,Integer, UUID>>> t){
        Set<int[]> nodes = new HashSet<>();
        int[] mm = t.getFirst();
        for(int x = mm[0] + 1; x < mm[2] ;x++){
            for(int y = mm[1] + 1; y < mm[3] ;y++){
                int ad = allDistances(x, y, t.getSecond());
                nodes.add(new int[]{x, y, ad});
            }
        }
        return nodes;
    }
}
