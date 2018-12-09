package nl.openweb.adventofcode2018.ivor.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * @author Ivor
 */
public class MarbleGame2 {

    private CircleElement<Long> currentElement;

    private final int playerCount;
    private final int lastMarbleWorth;

    public MarbleGame2(int playerCount, int lastMarbleWorth){
        this.playerCount = playerCount;
        this.lastMarbleWorth = lastMarbleWorth;

    }

    public List<Player<Long>> play() {
//        Map<Integer, List<Integer>> scores = new HashMap<>();
        List<Player<Long>> players = createPlayers();

        currentElement = createStartElement();
        int currentPlayerIndex = 0;
        long currentMarbleIndex = 0;
        long currentMarble = 1L;
        Rule<Long> rule = new LongRule();
        while (currentMarble <= lastMarbleWorth) {
            currentElement = currentElement.add(currentMarble, players.get(currentPlayerIndex), rule);
            currentMarble++;
            currentPlayerIndex = (currentPlayerIndex == (players.size() - 1)) ? 0 : currentPlayerIndex + 1;
        }

        return players;
    }

    private List<Player<Long>> createPlayers() {
        List<Player<Long>> players = new ArrayList<>();
        BinaryOperator<Long> summedLong = (aLong, aLong2) -> aLong + aLong2;
        for (int i = 1; i <= playerCount; i++) {
            players.add(new Player<>(i+1, summedLong, 0L));
        }
        return players;
    }

    private CircleElement<Long> createStartElement() {
        CircleElement<Long> element = new CircleElement<>();
        element.setValue(0L);
        element.setNext(element);
        element.setPrevious(element);
        return element;
    }
}
