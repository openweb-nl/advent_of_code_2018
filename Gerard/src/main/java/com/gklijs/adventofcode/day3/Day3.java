package com.gklijs.adventofcode.day3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day3 {

    private Day3(){
        //prevent instantiation
    }

    private static final Set<Integer> ONLY_ZERO = Collections.singleton(0);

    public static Single<Integer> multipleClaims(Observable<String> ids) {
        return ids
            .map(Day3::toRow)
            .reduce(new int[1000][1000], Day3::claim)
            .map(Day3::claimedMultiple);
    }

    public static Single<Integer> noClaims(Observable<String> ids) {
        return ids
            .map(Day3::toRow)
            .reduce(new Pair<>(new int[1000][1000], new HashSet<>()), Day3::claim2)
            .map(x -> x.getSecond().iterator().next());
    }

    private static int[] toRow(String patch){
        int[] result = new int[5];
        int startId = patch.indexOf('#') + 1;
        int startFromLeft = patch.indexOf('@') + 2;
        int startFromTop = patch.indexOf(',') + 1;
        int startWidth = patch.indexOf(':') + 2;
        int startHeight = patch.indexOf('x') + 1;
        result[0] = Integer.parseInt(patch.substring(startId, startFromLeft - 3));
        result[1] = Integer.parseInt(patch.substring(startFromLeft, startFromTop - 1));
        result[2] = Integer.parseInt(patch.substring(startFromTop, startWidth - 2));
        result[3] = Integer.parseInt(patch.substring(startWidth, startHeight - 1));
        result[4] = Integer.parseInt(patch.substring(startHeight));
        return result;
    }

    private static int[][] claim(int[][] fabric, int[] patch){
        for (int x = patch[1] ; x < patch[1] + patch[3] ; x ++){
            for (int y = patch[2] ; y < patch[2] + patch[4] ; y ++){
                fabric[x][y] = fabric[x][y] + 1;
            }
        }
        return fabric;
    }

    private static int claimedMultiple(int[][] fabric){
        int counter = 0;
        for(int[] row : fabric){
            for(int value : row){
                if(value > 1){
                    counter ++;
                }
            }
        }
        return counter;
    }

    private static void setId(int[][] fabric, int[] patch){
        for (int x = patch[1] ; x < patch[1] + patch[3] ; x ++){
            for (int y = patch[2] ; y < patch[2] + patch[4] ; y ++){
                fabric[x][y] = patch[0];
            }
        }
    }

    private static Set<Integer> currentIds(int[][] fabric, int[] patch){
        Set<Integer> ids = new HashSet<>();
        for (int x = patch[1] ; x < patch[1] + patch[3] ; x ++){
            for (int y = patch[2] ; y < patch[2] + patch[4] ; y ++){
                ids.add(fabric[x][y]);
            }
        }
        return ids;
    }

    private static Pair<int[][],Set<Integer>> claim2(Pair<int[][],Set<Integer>> pair, int[] patch){
        Set<Integer> currentClaims = currentIds(pair.getFirst(), patch);
        setId(pair.getFirst(), patch);
        if(ONLY_ZERO.equals(currentClaims)){
            pair.getSecond().add(patch[0]);
        }else{
            pair.getSecond().removeAll(currentClaims);
        }
        return pair;
    }
}
