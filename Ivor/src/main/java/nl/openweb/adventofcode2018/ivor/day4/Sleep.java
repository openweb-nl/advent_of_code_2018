package nl.openweb.adventofcode2018.ivor.day4;

import java.util.Objects;

/**
 * @author Ivor
 */
public class Sleep {
    private int fromMinute;
    private int toMinute;

    public int getFromMinute() {
        return fromMinute;
    }

    public void setFromMinute(int fromMinute) {
        this.fromMinute = fromMinute;
    }

    public int getToMinute() {
        return toMinute;
    }

    public void setToMinute(int toMinute) {
        this.toMinute = toMinute;
    }

    public int getDuration() {
        return toMinute - fromMinute;
    }

    public boolean isAsleepOn(int minute) {
        return minute >= fromMinute && minute < toMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sleep sleep = (Sleep) o;
        return fromMinute == sleep.fromMinute &&
                toMinute == sleep.toMinute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromMinute, toMinute);
    }
}
