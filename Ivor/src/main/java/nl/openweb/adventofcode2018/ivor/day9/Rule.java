package nl.openweb.adventofcode2018.ivor.day9;

/**
 * @author Ivor
 */
public interface Rule<T> {
    CircleElement<T> process(CircleElement<T> tCircleElement, T value, Player<T> player);
}
