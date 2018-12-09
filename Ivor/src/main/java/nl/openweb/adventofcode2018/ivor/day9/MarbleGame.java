package nl.openweb.adventofcode2018.ivor.day9;

import java.util.*;

/**
 * @author Ivor
 */
public class MarbleGame {

    private final int playerCount;
    private final int lastMarbleWorth;

    public MarbleGame(int playerCount, int lastMarbleWorth) {

        this.playerCount = playerCount;
        this.lastMarbleWorth = lastMarbleWorth;
    }

    public int getHighestScore(Map<Integer, List<Integer>> scores) {
        return scores.values().stream()
                .map(list -> list.stream().mapToInt(Integer::intValue).sum())
                .max(Integer::compareTo)
                .orElse(0);
    }

    public Optional<Integer> getPlayerByScore(Map<Integer, List<Integer>> scores, int score) {
        return scores.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                    .mapToInt(Integer::intValue).sum() == score)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public Integer getScoreForPlayer(Map<Integer, List<Integer>> scores, int player) {
        if (scores.containsKey(player)) {
            return scores.get(player).stream().mapToInt(Integer::intValue).sum();
        }
        return 0;
    }
    /**
     *
     * @return for every play a list of marbles that they won. The sum will be the score
     */
    public Map<Integer, List<Integer>> play() {
        Map<Integer, List<Integer>> scores = new HashMap<>();
        List<Integer> circle = new ArrayList<>();
        circle.add(0);

        int player = 1;
        int currentMarbleIndex = 0;
        int currentMarble = 1;
        while (currentMarble <= lastMarbleWorth) {

            if (currentMarble % 23 == 0) {
                addToScore(scores, player, currentMarble);
                currentMarbleIndex = getIndexToPlaceMarble(circle.size(), currentMarbleIndex, -7);
                addToScore(scores, player, circle.remove(currentMarbleIndex));
            } else {
                currentMarbleIndex = getIndexToPlaceMarble(circle.size(), currentMarbleIndex, 2);
                circle.add(currentMarbleIndex, currentMarble);
            }
//            System.out.println("[" + player + "]" + circle);
            currentMarble++;
            player = player == playerCount ? 1 : player+1;
        }
//        System.out.println("Scores: " + scores);
        return scores;
    }

    public int getIndexToPlaceMarble(int circleSize, int currentMarbleIndex, int step) {
        if (circleSize == 1) {
            return 1;
        }
        if (currentMarbleIndex + step < 0) {
            return currentMarbleIndex + step + circleSize;
        }
        if (currentMarbleIndex < circleSize-(step-1)) {
            return currentMarbleIndex + step;
        }
        return currentMarbleIndex + step - circleSize;
    }

    private void addToScore(Map<Integer, List<Integer>> scores, int player, int currentMarble) {
        if (!scores.containsKey(player)) {
            scores.put(player, new ArrayList<>());
        }
        scores.get(player).add(currentMarble);
    }
}
