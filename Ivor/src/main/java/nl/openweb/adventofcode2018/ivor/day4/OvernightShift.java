package nl.openweb.adventofcode2018.ivor.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ivor
 */
public class OvernightShift implements Comparable<OvernightShift> {

    private String timeStamp;
    private Integer guardId;
    private List<Sleep> sleeps = new ArrayList<>();


    public Integer getGuardId() {
        return guardId;
    }

    public void setGuardId(Integer guardId) {
        this.guardId = guardId;
    }

    public Integer getMinutesAsleep() {
        return sleeps.stream().mapToInt(Sleep::getDuration).sum();
    }


    public int getTimesAsleepOn(int minute) {
        return (int) sleeps.stream().filter(sleep -> sleep.isAsleepOn(minute)).count();
    }

    public List<Sleep> getSleeps() {
        return sleeps;
    }

    public void setSleeps(List<Sleep> sleeps) {
        this.sleeps = sleeps;
    }

    @Override
    public int compareTo(OvernightShift o) {
        return timeStamp.compareTo(o.timeStamp);
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OvernightShift that = (OvernightShift) o;
        return Objects.equals(timeStamp, that.timeStamp) &&
                Objects.equals(guardId, that.guardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeStamp, guardId);
    }

    public void startNewSleep(int minute) {
        Sleep sleep = new Sleep();
        sleep.setFromMinute(minute);
        sleeps.add(sleep);
    }

    public void endSleep(int minute) {
        sleeps.get(sleeps.size() - 1).setToMinute(minute);
    }
}
