package nl.openweb.adventofcode2018.ivor.day7;

import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
public class Step implements Comparable<Step> {
    private final String key;
    private final SortedSet<Step> prequisites = new TreeSet<>();

    public Step(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void addPrequisite(Step step) {
        prequisites.add(step);
    }

    public SortedSet<Step> getPrequisites() {
        return prequisites;
    }

    @Override
    public int compareTo(Step o) {
        return key.compareTo(o.key);
    }

    public boolean canStart(List<String> finishedSteps) {
        return prequisites.isEmpty() || finishedSteps.containsAll(prequisites.stream().map(o -> o.key).collect(Collectors.toList()));
    }

    public boolean canStart(Progress progress, int time) {
        // get steps that will be finished at that specified time
        List<String> finishedSteps = progress.getFinishedSteps().stream()
                .filter(cs -> cs.getTimeFinished() <= time)
                .map(cs -> cs.getStep().getKey())
                .collect(Collectors.toList());

        return prequisites.isEmpty() || finishedSteps.containsAll(prequisites.stream().map(o -> o.key).collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return Objects.equals(key, step.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "Step " + getKey();
    }
}
