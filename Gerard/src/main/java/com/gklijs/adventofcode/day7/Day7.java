package com.gklijs.adventofcode.day7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day7 {

    private Day7(){
        //prevent instantiation
    }

    public static Single<String> getOrder(Observable<String> possibleSteps) {
        return possibleSteps
            .map(Day7::toInstruction)
            .reduce(new HashMap<>(), Day7::toGraph)
            .map(Day7::order)
            .map(x -> x.stream().map(Object::toString).collect(Collectors.joining()));
    }

    public static Single<String> work(Observable<String> possibleSteps, int workers, int additionalSeconds) {
        return possibleSteps
            .map(Day7::toInstruction)
            .reduce(new HashMap<>(), Day7::toGraph)
            .map(graph -> go(graph, workers, additionalSeconds))
            .map(Object::toString);
    }

    private static Pair<Character, Character> toInstruction(String rep){
        return new Pair<>(rep.charAt(5), rep.charAt(36));
    }

    private static Map<Character, Set<Character>> toGraph(Map<Character, Set<Character>> result, Pair<Character, Character> path){
        if(result.containsKey(path.getSecond())){
            result.get(path.getSecond()).add(path.getFirst());
        }else{
            Set<Character> set = new HashSet<>();
            set.add(path.getFirst());
            result.put(path.getSecond(), set);
        }
        if(!result.containsKey(path.getFirst())){
            result.put(path.getFirst(), new HashSet<>());
        }
        return result;
    }

    private static List<Character> order(Map<Character, Set<Character>> graph){
        List<Character> path = new ArrayList<>();
        Character nextChar = nextTask(graph, path);
        while(nextChar != null){
            graph.remove(nextChar);
            path.add(nextChar);
            nextChar = nextTask(graph, path);
        }
        return path;
    }

    private static boolean isValid(Map.Entry<Character, Set<Character>> entry, Collection<Character> path) {
        Set<Character> set = new HashSet<>(entry.getValue());
        set.removeAll(path);
        return set.isEmpty();
    }

    private static Character nextTask(final Map<Character, Set<Character>> graph, final Collection<Character> path) {
        return graph.entrySet().stream()
            .filter(x -> isValid(x, path))
            .map(Map.Entry::getKey)
            .sorted()
            .findFirst()
            .orElse(null);
    }

    private static Integer go(final Map<Character, Set<Character>> graph, final int workers, final int additionalSeconds) {
        int workersWorking = 0;
        List<Character> done = new ArrayList<>();
        Map<Character, Integer> atWork = new HashMap<>();
        int timeSpend = 0;
        while(! graph.isEmpty()){
            Character nextTask = nextTask(graph, done);
            while(nextTask != null && workersWorking < workers){
                workersWorking++;
                atWork.put(nextTask, nextTask - 64 + additionalSeconds);
                graph.remove(nextTask);
                nextTask = nextTask(graph, done);
            }
            int progresTimeBy = atWork.values().stream().reduce(Integer.MAX_VALUE, (o,n) -> n < o ? n : o);
            timeSpend+= progresTimeBy;
            List<Character> justCompleted = atWork.entrySet().stream()
                .peek(x -> x.setValue(x.getValue() - progresTimeBy))
                .filter(x -> x.getValue() <= 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
            atWork.keySet().removeAll(justCompleted);
            workersWorking -= justCompleted.size();
            done.addAll(justCompleted);
        }
        return timeSpend;
    }
}
