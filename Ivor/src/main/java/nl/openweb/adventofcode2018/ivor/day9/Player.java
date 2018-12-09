package nl.openweb.adventofcode2018.ivor.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @author Ivor
 */
public class Player<T> {
    private int id;
    private List<T> scoredMarbles = new ArrayList<>();
    private final BinaryOperator<T> operator;
    private final T defaultValue;

    public Player(int id, BinaryOperator<T> operator, T defaultValue) {
        this.id = id;
        this.operator = operator;
        this.defaultValue = defaultValue;
    }


    public void addMarble(T value) {
        scoredMarbles.add(value);
    }

    public T getScore() {
        return scoredMarbles.stream().reduce(operator).orElse(defaultValue);
    }

    @Override
    public String toString() {
        return "[" + id + "] score=" + getScore();
    }

    public int getId() {
        return id;
    }
}
