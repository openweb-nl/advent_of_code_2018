package nl.openweb.adventofcode2018.ivor.day9;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ivor
 */
public class Day9 {


    public static void main(String... args) {
        MarbleGame game = new MarbleGame(405, 71700);
        Map<Integer, List<Integer>> scores = game.play();
        int highestScore = game.getHighestScore(scores);
        System.out.println("Score: " + highestScore);

        MarbleGame2 game1 = new MarbleGame2(405, 71700);
        List<Player<Long>> players1 = game1.play();
        Optional<Player<Long>> winningPlayer = players1.stream().max(Comparator.comparing(Player::getScore));
        if (winningPlayer.isPresent()) {
            System.out.println("Q1 Player " + winningPlayer.get());
        }

        MarbleGame2 game2 = new MarbleGame2(405, 7170000);
        List<Player<Long>> players2 = game2.play();
        Optional<Player<Long>> winningPlayer2 = players2.stream().max(Comparator.comparing(Player::getScore));
        if (winningPlayer2.isPresent()) {
            System.out.println("Q2 Player " + winningPlayer2.get());
        }
        System.out.println("DONE");

    }
}
