package com.gklijs.adventofcode.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gklijs.adventofcode.Utils;
import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;
import static io.reactivex.Observable.concat;

public class Day5 {

    private Day5() {
        //prevent instantiation
    }

    public static Single<Integer> react(Observable<String> polymer) {
        return concat(polymer, Single.just("").repeat().toObservable())
            .scan(new Pair<>(new ArrayList<>(), -1), Day5::doReaction)
            .takeUntil(result -> result.getSecond() == 0)
            .lastOrError()
            .map(result -> result.getFirst().size());
    }

    public static Single<Integer> reactDeleteReact(Observable<String> polymer) {
        return concat(polymer, Single.just("").repeat().toObservable())
            .scan(new Pair<>(new ArrayList<>(), -1), Day5::doReaction)
            .takeUntil(result -> result.getSecond() == 0)
            .lastOrError()
            .map(Pair::getFirst)
            .map(Day5::variants)
            .flattenAsFlowable(g -> g)
            .map(Day5::sizeReactChild)
            .reduce(Integer.MAX_VALUE, Math::min);
    }

    private static Pair<List<Character>, Integer> doReaction(Pair<List<Character>, Integer> result, String arg){
        if (arg.isEmpty()) {
            List<Integer> ids = toBeRemoved(result.getFirst());
            int correction = 0;
            for(int id : ids){
                result.getFirst().remove(id - correction);
                correction++;
            }
            result.setSecond(ids.size());
        }else {
            result.getFirst().addAll(Utils.toList(arg));
        }
        return result;
    }

    private static List<Integer> toBeRemoved(List<Character> polymer){
        List<Integer> ids = new ArrayList<>();
        boolean skipNext = false;
        for(int i = 1; i < polymer.size(); i++){
            int diff = polymer.get(i) - polymer.get(i-1);
            if(!skipNext && Math.abs(diff) == 32){
                ids.add(i -1);
                ids.add(i);
                skipNext = true;
            }else{
                skipNext = false;
            }
        }
        return ids;
    }

    private static List<Character>  dedupe(List<Character> characters) {
        Set<Character> characterSet = new HashSet<>();
        for(Character character : characters){
            characterSet.add(Character.toLowerCase(character));
        }
        return new ArrayList<>(characterSet);
    }

    private static int sizeReactChild (List<Character> polymer){
        Pair<List<Character>, Integer> result = new Pair<>(polymer, -1);
        while(result.getSecond() != 0){
            doReaction(result, "");
        }
        return result.getFirst().size();
    }

    private static List<List<Character>> variants(final List<Character> characters) {
        List<Character> dedupes = dedupe(characters);
        List<List<Character>> variants = new ArrayList<>();
        for(Character dedupe : dedupes){
            List<Character> variant = new ArrayList<>(characters);
            variant.removeAll(Arrays.asList(dedupe, Character.toUpperCase(dedupe)));
            variants.add(variant);
        }
        return variants;
    }
}