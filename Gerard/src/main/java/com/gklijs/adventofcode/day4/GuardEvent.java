package com.gklijs.adventofcode.day4;

public class GuardEvent implements Comparable<GuardEvent> {

    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    final int minute;
    final GuardEventType guardEventType;
    int id;

    GuardEvent(String guardEvent) {
        year = Integer.parseInt(guardEvent.substring(1, 5));
        month = Integer.parseInt(guardEvent.substring(6, 8));
        day = Integer.parseInt(guardEvent.substring(9, 11));
        hour = Integer.parseInt(guardEvent.substring(12, 14));
        minute = Integer.parseInt(guardEvent.substring(15, 17));
        guardEventType = GuardEventType.get(guardEvent.charAt(19));
        if (guardEventType == GuardEventType.BEGINS_SHIFT) {
            int fourthSpace = guardEvent.indexOf(' ', 26);
            id = Integer.parseInt(guardEvent.substring(26, fourthSpace));
        } else {
            id = -1;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object instanceof GuardEvent) {
            GuardEvent other = (GuardEvent) object;
            return year == other.year && month == other.month && day == other.day && hour == other.hour && minute == other.minute;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + year;
        result = 31 * result + month;
        result = 31 * result + day;
        result = 31 * result + hour;
        result = 31 * result + minute;
        return result;
    }

    @Override
    public int compareTo(final GuardEvent other) {
        int result = Integer.compare(year, other.year);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(month, other.month);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(day, other.day);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(hour, other.hour);
        return result != 0 ? result : Integer.compare(minute, other.minute);
    }
}
