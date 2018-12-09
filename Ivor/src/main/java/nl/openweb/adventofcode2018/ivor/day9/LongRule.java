package nl.openweb.adventofcode2018.ivor.day9;

/**
 * @author Ivor
 */
public class LongRule implements Rule<Long> {
    @Override
    public CircleElement<Long> process(CircleElement<Long> circleElement, Long value, Player<Long> player) {
        if (value % 23 == 0) {
            player.addMarble(value);
            CircleElement<Long> element = circleElement;
            for (int i = 0; i < 7; i++) {
                element = element.getPrevious();
            }
            player.addMarble(element.getValue());
            return element.remove();
//
//            addToScore(scores, player, currentMarble);
//            currentMarbleIndex = getIndexToPlaceMarble(circle.size(), currentMarbleIndex, -7);
//            addToScore(scores, player, circle.remove(currentMarbleIndex));
        } else {
            return circleElement.getNext().insertAfter(value);
        }
    }
}
