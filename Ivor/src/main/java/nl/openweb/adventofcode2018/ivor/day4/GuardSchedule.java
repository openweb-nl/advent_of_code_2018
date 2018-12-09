package nl.openweb.adventofcode2018.ivor.day4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivor
 */
public class GuardSchedule {
    private Map<Integer, List<OvernightShift>> shiftsPerGuard = new HashMap<>();
    private OvernightShift activeShift = new OvernightShift();



    public void addLogLine(String line) {
        if (line.contains("Guard #")) {
            activeShift = new OvernightShift();
            activeShift.setTimeStamp(line.substring(6, 16));
            Integer guardId = Integer.parseInt(line.substring( line.indexOf("Guard #") + 7, line.indexOf(" begins")).trim());
            activeShift.setGuardId(guardId);
            if (!shiftsPerGuard.containsKey(guardId)) {
                shiftsPerGuard.put(guardId, new ArrayList<>());
            }
            shiftsPerGuard.get(guardId).add(activeShift);

        } else {
            int minute = Integer.parseInt(line.substring(15, 17));
            if (line.contains("falls asleep")) {
                activeShift.startNewSleep(minute);
            } else if (line.contains("wakes up")){
                activeShift.endSleep(minute);
            }

        }
    }

    public Map<Integer, List<OvernightShift>> getShiftsPerGuard() {
        return shiftsPerGuard;
    }

    public Integer getGuardWithMostMinutesAsleep() {
        Integer result = null;
        int max = 0;
        for (Integer id : shiftsPerGuard.keySet()) {
            int total = shiftsPerGuard.get(id).stream().mapToInt(OvernightShift::getMinutesAsleep).sum();
            if (total > max) {
                result = id;
                max = total;
            }
        }
        return result;
    }

    public Pair<Integer, Integer> getMinuteMostAsleep(Integer guardId) {
//        Integer result = null;
//        int result = 0;
        Pair<Integer, Integer> result = null;
        for (int minute = 0; minute < 60; minute++) {
            int finalMinute = minute;
            int count = shiftsPerGuard.get(guardId).stream().mapToInt(shift -> shift.getTimesAsleepOn(finalMinute)).sum();
            if (result == null || count > result.getTwo()) {
                result = new Pair<>(minute, count);
            }
        }
        return result;
    }

}
