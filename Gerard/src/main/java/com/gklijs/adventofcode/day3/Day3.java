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
            .map(Patch::new)
            .reduce(new int[1000][1000], Day3::claim)
            .map(Day3::claimedMultiple);
    }

    public static Single<Integer> noClaims(Observable<String> ids) {
        return ids
            .map(Patch::new)
            .reduce(new Pair<>(new int[1000][1000], new HashSet<>()), Day3::claim2)
            .map(x -> x.getSecond().iterator().next());
    }

    private static int[][] claim(int[][] fabric, Patch patch){
        for (int x = patch.fromLeft ; x < patch.fromLeft + patch.width ; x ++){
            for (int y = patch.fromTop ; y < patch.fromTop + patch.height ; y ++){
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

    private static void setId(int[][] fabric, Patch patch){
        for (int x = patch.fromLeft ; x < patch.fromLeft + patch.width ; x ++){
            for (int y = patch.fromTop ; y < patch.fromTop + patch.height ; y ++){
                fabric[x][y] = patch.id;
            }
        }
    }

    private static Set<Integer> currentIds(int[][] fabric, Patch patch){
        Set<Integer> ids = new HashSet<>();
        for (int x = patch.fromLeft ; x < patch.fromLeft + patch.width ; x ++){
            for (int y = patch.fromTop ; y < patch.fromTop + patch.height ; y ++){
                ids.add(fabric[x][y]);
            }
        }
        return ids;
    }

    private static Pair<int[][],Set<Integer>> claim2(Pair<int[][],Set<Integer>> pair, Patch patch){
        Set<Integer> currentClaims = currentIds(pair.getFirst(), patch);
        setId(pair.getFirst(), patch);
        if(ONLY_ZERO.equals(currentClaims)){
            pair.getSecond().add(patch.id);
        }else{
            pair.getSecond().removeAll(currentClaims);
        }
        return pair;
    }
}
