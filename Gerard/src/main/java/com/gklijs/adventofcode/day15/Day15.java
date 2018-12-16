package com.gklijs.adventofcode.day15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gklijs.adventofcode.Utils;
import com.gklijs.adventofcode.utils.Pair;
import com.gklijs.adventofcode.utils.Triple;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day15 {

    private Day15() {
        //prevent instantiation
    }

    public static Single<String> lastTurn(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Utils::addString)
            .map(Day15::addCreatures)
            .map(Day15::fight)
            .map(Day15::answer);
    }

    public static Single<String> noDeadElf(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Utils::addString)
            .map(Day15::addCreatures)
            .map(Day15::noElfDies)
            .map(Day15::answer);
    }

    private static String answer(List<Creature> input) {
        int hitPointsRemaining = input.stream().mapToInt(Creature::getHitpoints).sum();
        Collections.sort(input);
        int result = input.get(0).getStepsTaken() * hitPointsRemaining;
        return Integer.toString(result);
    }

    private static Pair<List<char[]>, List<Creature>> addCreatures(List<char[]> map) {
        List<Creature> creatures = new ArrayList<>();
        for (int y = 0; y < map.size(); y++) {
            char[] line = map.get(y);
            for (int x = 0; x < line.length; x++) {
                if (line[x] == 'G') {
                    creatures.add(new Goblin(x, y));
                } else if (line[x] == 'E') {
                    creatures.add(new Elf(x, y));
                }
            }
        }
        return new Pair<>(map, creatures);
    }

    private static boolean isFree(Pair<Integer, Integer> cord, List<char[]> map) {
        return map.get(cord.getSecond())[cord.getFirst()] == '.';
    }

    private static boolean isTarget(Pair<Integer, Integer> cord, List<char[]> map, char target) {
        return map.get(cord.getSecond())[cord.getFirst()] == target;
    }

    private static void updateMap(Pair<Integer, Integer> cord, List<char[]> map, Move move) {
        if (move == Move.NOT) {
            return;
        }
        char current = map.get(cord.getSecond())[cord.getFirst()];
        map.get(cord.getSecond())[cord.getFirst()] = '.';
        Pair<Integer, Integer> next = move.nextCord(cord);
        map.get(next.getSecond())[next.getFirst()] = current;
    }

    private static void handleOption(Pair<Integer, Integer> option, Move move,
                                     EnumMap<Move, Pair<List<Pair<Integer, Integer>>, Set<Pair<Integer, Integer>>>> paths,
                                     List<Pair<Integer, Integer>> next,
                                     List<Triple<Integer, Integer, Move>> targets,
                                     List<char[]> map, char target) {
        if (!paths.get(move).getSecond().contains(option)) {
            if (isTarget(option, map, target)) {
                paths.get(move).getSecond().add(option);
                targets.add(new Triple<>(option.getSecond(), option.getFirst(), move));
            } else if (isFree(option, map)) {
                paths.get(move).getSecond().add(option);
                next.add(option);
            }
        }
    }

    private static void nextMove(EnumMap<Move, Pair<List<Pair<Integer, Integer>>, Set<Pair<Integer, Integer>>>> paths,
                                 List<Triple<Integer, Integer, Move>> targets,
                                 List<char[]> map, char target, boolean firstIter, Move move) {
        List<Pair<Integer, Integer>> endpoints = paths.get(move).getFirst();
        List<Pair<Integer, Integer>> next = new ArrayList<>();
        for (Move nextMove : Move.values()) {
            if (nextMove == Move.NOT || (firstIter && Move.skip(move, nextMove) && paths.containsKey(nextMove))) {
                continue;
            }
            for (Pair<Integer, Integer> endpoint : endpoints) {
                handleOption(nextMove.nextCord(endpoint), move, paths, next, targets, map, target);
            }
        }
        if (next.isEmpty()) {
            paths.remove(move);
        } else {
            paths.get(move).setFirst(next);
        }
    }

    private static void nextStep(EnumMap<Move, Pair<List<Pair<Integer, Integer>>, Set<Pair<Integer, Integer>>>> paths,
                                 List<Triple<Integer, Integer, Move>> targets,
                                 List<char[]> map, char target, boolean firstIter) {
        for (Move move : Move.values()) {
            if (paths.containsKey(move)) {
                nextMove(paths, targets, map, target, firstIter, move);
            }
        }
    }

    private static Move nextMove(Pair<Integer, Integer> current, List<char[]> map, char target) {
        EnumMap<Move, Pair<List<Pair<Integer, Integer>>, Set<Pair<Integer, Integer>>>> paths = new EnumMap<>(Move.class);
        List<Triple<Integer, Integer, Move>> targets = new ArrayList<>();
        for (Move move : Move.values()) {
            Pair<Integer, Integer> next = move.nextCord(current);
            if (isTarget(next, map, target)) {
                return Move.NOT;
            }
            if (isFree(next, map)) {
                List<Pair<Integer, Integer>> path = new ArrayList<>();
                path.add(next);
                Set<Pair<Integer, Integer>> history = new HashSet<>();
                history.add(next);
                paths.put(move, new Pair<>(path, history));
            }
        }
        boolean firstIter = true;
        while (!paths.isEmpty() && targets.isEmpty()) {
            nextStep(paths, targets, map, target, firstIter);
            firstIter = false;
        }
        if (targets.isEmpty()) {
            return Move.NOT;
        } else {
            targets.sort((t1, t2) -> t1.getFirst().equals(t2.getFirst()) ? t1.getSecond() - t2.getSecond() : t1.getFirst() - t2.getFirst());
            return targets.get(0).getThird();
        }
    }

    private static boolean bothCampsAlive(List<Creature> creatures) {
        int elfs = 0;
        int goblins = 0;
        for (Creature creature : creatures) {
            if (creature instanceof Elf) {
                elfs++;
            } else if (creature instanceof Goblin) {
                goblins++;
            }
        }
        return elfs != 0 && goblins != 0;
    }

    private static Creature getCreatureFrom(Pair<Integer, Integer> location, List<Creature> creatures) {
        for (Creature creature : creatures) {
            if (creature.getCord().equals(location)) {
                return creature;
            }
        }
        return null;
    }

    private static void performAttack(Creature creature, List<Creature> creatures, List<char[]> map) {
        List<Creature> potentialTargets = new ArrayList<>();
        for (Move move : Move.values()) {
            Pair<Integer, Integer> next = move.nextCord(creature.getCord());
            if (isTarget(next, map, creature.getTarget())) {
                potentialTargets.add(getCreatureFrom(next, creatures));
            }
        }
        if (potentialTargets.isEmpty()) {
            return;
        }
        int minhealth = potentialTargets.stream().mapToInt(Creature::getHitpoints).min().orElse(-1);
        for (Creature target : potentialTargets) {
            if (target.getHitpoints() == minhealth) {
                int hp = target.takeHit(creature.getPower());
                if (hp <= 0) {
                    creatures.remove(target);
                    map.get(target.getCord().getSecond())[target.getCord().getFirst()] = '.';
                }
                return;
            }
        }
    }

    private static List<Creature> fight(Pair<List<char[]>, List<Creature>> input) {
        List<char[]> map = input.getFirst();
        List<Creature> creatures = input.getSecond();
        while (bothCampsAlive(creatures)) {
            Collections.sort(creatures);
            Creature actor = creatures.get(0);
            Move nextMove = nextMove(actor.getCord(), map, actor.getTarget());
            updateMap(actor.getCord(), map, nextMove);
            actor.move(nextMove);
            performAttack(actor, creatures, map);
        }
        return creatures;
    }

    private static List<Creature> noElfDies(Pair<List<char[]>, List<Creature>> input) {
        List<char[]> map = input.getFirst();
        List<Creature> creatures = input.getSecond();
        long elfsAlive = creatures.stream().filter(x -> x instanceof Elf).count();
        for (int power = 4; power < 201; power++) {
            List<char[]> newMap = new ArrayList<>();
            for (char[] chars : map) {
                newMap.add(chars.clone());
            }
            List<Creature> newCreatures = new ArrayList<>();
            for (Creature creature : creatures) {
                if (creature instanceof Elf) {
                    newCreatures.add(new Elf(creature.getCord().getFirst(), creature.getCord().getSecond(), power));
                } else {
                    newCreatures.add(new Goblin(creature.getCord().getFirst(), creature.getCord().getSecond()));
                }
            }
            List<Creature> result = fight(new Pair<>(newMap, newCreatures));
            if (result.stream().filter(x -> x instanceof Elf).count() == elfsAlive) {
                return result;
            }
        }
        return Collections.emptyList();
    }
}
