package nl.openweb.adventofcode2018.ivor.day9;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ivor
 */
public class Day9Test {

    @Test
    public void testExample1() {
        MarbleGame game = new MarbleGame(9, 25);
        Map<Integer, List<Integer>> scores = game.play();
        assertEquals(32, game.getHighestScore(scores));
    }

    @Test
    public void testExample2() {
        MarbleGame game = new MarbleGame(30, 5807);
        Map<Integer, List<Integer>> scores = game.play();
        assertEquals(37305, game.getHighestScore(scores));
    }


    @Test
    public void testIndexToPlaceMarble() {
        MarbleGame game = new MarbleGame(9, 25);
        assertEquals(1, game.getIndexToPlaceMarble(1, 0, 2));
        assertEquals(1, game.getIndexToPlaceMarble(2, 1, 2));
        assertEquals(3, game.getIndexToPlaceMarble(3, 1, 2));
        assertEquals(1, game.getIndexToPlaceMarble(4, 3, 2));
        assertEquals(6, game.getIndexToPlaceMarble(23, 13, -7));
        assertEquals(19, game.getIndexToPlaceMarble(23, 3, -7));
    }


    @Test
    public void testQ2() {
        MarbleGame2 game2 = new MarbleGame2(30, 5807);
        List<Player<Long>> players = game2.play();
        Optional<Player<Long>> maxPlayer = players.stream().max((p1, p2) -> p1.getScore().compareTo(p2.getScore()));
        assertTrue(maxPlayer.isPresent());
        assertEquals(Long.valueOf(37305L), maxPlayer.get().getScore());

    }

}
