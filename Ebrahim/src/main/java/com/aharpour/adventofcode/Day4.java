package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.IntPair;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {

    protected Map<Integer, List<Day>> schedule;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final Pattern PATTERN = Pattern.compile("\\[(.*)\\] (.*)");
    private static final Pattern SHIFT_PATTERN = Pattern.compile("Guard #(\\d+) begins shift");

    protected IntPair getMax(int[] acuSleep) {
        int max = 0;
        int indexOfMax = 0;
        for (int minute = 0; minute < acuSleep.length; minute++) {
            int v = acuSleep[minute];
            if (v > max) {
                max = v;
                indexOfMax = minute;
            }
        }
        return new IntPair(indexOfMax, max);
    }

    protected int[] getGuardAcuSleep(List<Day> days) {
        int[] ints = new int[60];
        for (int i = 0; i < 60; i++) {
            for (Day day : days) {
                if (day.sleepPattern[i]) {
                    ints[i]++;
                }
            }
        }
        return ints;
    }

    protected void parseInput(String string) {
        schedule = Arrays.stream(string.split("\\s*\\n\\s*"))
                .map(PATTERN::matcher)
                .map(this::matcherToEntry)
                .collect(Collectors.groupingBy(Entry::getDay))
                .entrySet()
                .stream()
                .map(this::toDay)
                .collect(Collectors.groupingBy(Day::getGuard));
    }

    private Day toDay(Map.Entry<String, List<Entry>> mapEntry) {
        String day = mapEntry.getKey();
        List<Entry> value = mapEntry.getValue();
        value.sort(Comparator.comparing(Entry::getMinute));
        Integer guard = value.get(0).getGuard();
        boolean sleep = false;
        int fallSleepAt = 0;
        boolean[] sleepPattern = new boolean[60];
        for (int i = 1; i < value.size(); i++) {
            Entry entry = value.get(i);
            if (entry.sleep && !sleep) {
                sleep = true;
                fallSleepAt = entry.minute;
            }
            if (!entry.sleep && sleep) {
                sleep = false;
                setToTrue(sleepPattern, fallSleepAt, entry.minute);
            }
        }
        Entry lastItem = value.get(value.size() - 1);
        if (lastItem.sleep && sleep) {
            setToTrue(sleepPattern, fallSleepAt, 60);
        }
        return new Day(day, guard, sleepPattern);
    }

    private void setToTrue(boolean[] sleepPattern, int fallSleepAt, int minute) {
        if (fallSleepAt > minute || minute > sleepPattern.length) {
            throw new IllegalArgumentException();
        }
        for (int i = fallSleepAt; i < minute; i++) {
            sleepPattern[i] = true;

        }
    }

    private Entry matcherToEntry(Matcher matcher) {
        if (matcher.matches()) {
            Calendar ca = toCalendar(matcher.group(1));
            String text = matcher.group(2);
            return Entry.builder()
                    .minute(getMin(ca))
                    .day(getDay(ca))
                    .guard(getGuard(text))
                    .sleep(getSleep(text))
                    .build();
        }
        throw new IllegalArgumentException();
    }

    private boolean getSleep(String text) {
        boolean result = false;
        if (text.contains("falls asleep")) {
            result = true;
        }
        return result;
    }

    private Integer getGuard(String text) {
        Integer result = null;
        Matcher matcher = SHIFT_PATTERN.matcher(text);
        if (matcher.find()) {
            result = Integer.parseInt(matcher.group(1));
        }
        return result;
    }

    private String getDay(Calendar ca) {
        int h = ca.get(Calendar.HOUR_OF_DAY);
        if (h == 23) {
            ca.add(Calendar.DAY_OF_YEAR, 1);
        }
        return outputFormat.format(ca.getTime());
    }

    private int getMin(Calendar ca) {
        int min = ca.get(Calendar.MINUTE);
        int h = ca.get(Calendar.HOUR_OF_DAY);
        if (h == 23) {
            min -= 60;
        }
        return min;
    }

    @SneakyThrows
    private Calendar toCalendar(String date) {
        Calendar result = Calendar.getInstance();
        result.setTime(dateFormat.parse(date));
        return result;
    }

    @Data
    @Builder
    private static class Entry {
        private String day;
        private int minute;
        private Integer guard;
        private boolean sleep;
    }

    @Data
    protected static class Day {
        private String day;
        private int guard;
        private boolean[] sleepPattern;
        private int totalSleep = 0;

        private Day(String day, int guard, boolean[] sleepPattern) {
            this.day = day;
            this.guard = guard;
            this.sleepPattern = sleepPattern;
            for (boolean b : sleepPattern) {
                if (b) {
                    totalSleep++;
                }
            }

        }
    }

}
