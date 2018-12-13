package com.gklijs.adventofcode.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gklijs.adventofcode.utils.Pair;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Day13 {

    private Day13() {
        //prevent instantiation
    }

    public static Single<String> firstCrash(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Day13::addString)
            .map(Day13::addMineCards)
            .map(Day13::tillCrash)
            .map(Day13::display);
    }

    public static Single<String> lastCard(Observable<String> input) {
        return input
            .reduce(new ArrayList<>(), Day13::addString)
            .map(Day13::addMineCards)
            .map(Day13::tillLast)
            .map(Day13::display);
    }

    private static List<char[]> addString(List<char[]> tracks, String line) {
        tracks.add(line.toCharArray());
        return tracks;
    }

    private static Pair<List<char[]>, List<MineCart>> addMineCards(List<char[]> tracks) {
        List<MineCart> mineCarts = new ArrayList<>();
        for (int y = 0; y < tracks.size(); y++) {
            char[] line = tracks.get(y);
            for (int x = 0; x < line.length; x++) {
                if (Direction.validDirection(line[x])) {
                    mineCarts.add(new MineCart(x, y, line[x]));
                }
            }
        }
        return new Pair<>(tracks, mineCarts);
    }

    private static boolean firstCrashed(List<MineCart> mineCarts) {
        int x = mineCarts.get(0).getCord().getFirst();
        int y = mineCarts.get(0).getCord().getSecond();
        for (int i = 1; i < mineCarts.size(); i++) {
            MineCart cart = mineCarts.get(i);
            if (cart.getCord().getFirst() == x && cart.getCord().getSecond() == y) {
                return true;
            }
        }
        return false;
    }

    private static Pair<List<char[]>, List<MineCart>> tillCrash(Pair<List<char[]>, List<MineCart>> result) {
        while (!firstCrashed(result.getSecond())) {
            Collections.sort(result.getSecond());
            result.getSecond().get(0).move(result.getFirst());
        }
        return result;
    }

    private static int detectCrashed(List<MineCart> mineCarts) {
        int x = mineCarts.get(0).getCord().getFirst();
        int y = mineCarts.get(0).getCord().getSecond();
        for (int i = 1; i < mineCarts.size(); i++) {
            MineCart cart = mineCarts.get(i);
            if (cart.getCord().getFirst() == x && cart.getCord().getSecond() == y) {
                return i;
            }
        }
        return -1;
    }

    private static Pair<List<char[]>, List<MineCart>> tillLast(Pair<List<char[]>, List<MineCart>> result) {
        while (result.getSecond().size() > 1) {
            Collections.sort(result.getSecond());
            result.getSecond().get(0).move(result.getFirst());
            int i = detectCrashed(result.getSecond());
            if (i > 0) {
                int steps = result.getSecond().get(0).stepsTaken();
                result.getSecond().remove(i);
                result.getSecond().remove(0);
                if (result.getSecond().size() == 1 && steps > result.getSecond().get(0).stepsTaken()) {
                    result.getSecond().get(0).move(result.getFirst());
                }
            }
        }
        return result;
    }

    private static String display(Pair<List<char[]>, List<MineCart>> result) {
        Pair<Integer, Integer> coord = result.getSecond().get(0).getCord();
        return coord.getFirst() + "," + coord.getSecond();
    }
}
