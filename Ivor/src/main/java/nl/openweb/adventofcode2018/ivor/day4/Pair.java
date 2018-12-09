package nl.openweb.adventofcode2018.ivor.day4;

/**
 * @author Ivor
 */
public class Pair<ONE, TWO> {
    private final ONE one;
    private final TWO two;


    public Pair(ONE one, TWO two) {
        this.one = one;
        this.two = two;
    }

    public ONE getOne() {
        return one;
    }

    public TWO getTwo() {
        return two;
    }
}
