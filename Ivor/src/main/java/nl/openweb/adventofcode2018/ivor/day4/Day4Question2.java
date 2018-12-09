package nl.openweb.adventofcode2018.ivor.day4;

import java.util.List;
import java.util.Map;

/**
 * @author Ivor
 */
public class Day4Question2 extends Day4Question1 {

    public static void main(String... args) {
        int answer = new Day4Question2().getAnswer();
        System.out.println("Answer to q2: " + answer);
    }

    int getAnswer() {
        GuardSchedule guardSchedule = getGuardSchedule();
        Pair<Integer, Integer> maxMinuteCount = null;
        Integer guardId = null;
        for (Map.Entry<Integer, List<OvernightShift>> entry : guardSchedule.getShiftsPerGuard().entrySet()) {
            Pair<Integer, Integer> minuteWithCount = guardSchedule.getMinuteMostAsleep(entry.getKey());
            if (maxMinuteCount == null || minuteWithCount.getTwo() > maxMinuteCount.getTwo()) {
                maxMinuteCount = minuteWithCount;
                guardId = entry.getKey();
            }
        }
        return guardId * maxMinuteCount.getOne();
    }
}
