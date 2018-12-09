package nl.openweb.adventofcode2018.ivor.day9;

/**
 * @author Ivor
 */
public class CircleElement<T> {
    private T value;
    private CircleElement<T> previous;
    private CircleElement<T> next;


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public CircleElement<T> getPrevious() {
        return previous;
    }

    public void setPrevious(CircleElement<T> previous) {
        this.previous = previous;
    }

    public CircleElement<T> getNext() {
        return next;
    }

    public void setNext(CircleElement<T> next) {
        this.next = next;
    }

    public CircleElement<T> add(T value, Player<T> player, Rule<T> rule) {
        return rule.process(this, value, player);
    }

    public CircleElement<T> remove() {
        CircleElement<T> previous = getPrevious();
        CircleElement<T> next = getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        this.setPrevious(null);
        this.setNext(null);

        return next;
    }

    public CircleElement<T> insertAfter(T value) {
        CircleElement<T> element = new CircleElement<>();
        element.setValue(value);
        element.setPrevious(this);
        element.setNext(getNext());
        getNext().setPrevious(element);
        setNext(element);
        return element;
    }
}
