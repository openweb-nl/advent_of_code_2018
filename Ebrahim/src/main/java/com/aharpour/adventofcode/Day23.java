package com.aharpour.adventofcode;

import com.aharpour.adventofcode.utils.geometry.ThreeDPoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {

    protected int strongestIndex;
    private static final Pattern PATTERN = Pattern.compile("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)");
    protected List<NanoBot> nanoBots = new ArrayList<>();


    public Day23(String input) {
        Matcher matcher = PATTERN.matcher(input);
        int maxRadius = Integer.MIN_VALUE;
        while (matcher.find()) {
            NanoBot nanoBot = matcherToNanoBot(matcher);
            nanoBots.add(nanoBot);
            if (nanoBot.radious > maxRadius) {
                maxRadius = nanoBot.radious;
                strongestIndex = nanoBots.size() - 1;
            }
        }
    }


    private NanoBot matcherToNanoBot(Matcher matcher) {
        return new NanoBot(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    protected class NanoBot extends ThreeDPoint {

        private final int radious;

        private NanoBot(int x, int y, int z, int radious) {
            super(x, y, z);
            this.radious = radious;
        }
    }
}
