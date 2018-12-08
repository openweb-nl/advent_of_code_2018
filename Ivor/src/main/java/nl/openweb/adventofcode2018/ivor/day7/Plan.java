package nl.openweb.adventofcode2018.ivor.day7;

import nl.openweb.adventofcode2018.ivor.day4.Pair;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
public class Plan {
    private final SortedMap<String, Step> steps = new TreeMap<>();

    public void addLine(String line) {
        String regex = "Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches() && matcher.groupCount() == 2) {
            String key1 = matcher.group(1);
            String key2 = matcher.group(2);
            getStep(key2).addPrequisite(getStep(key1));
        } else {
            System.out.println("Failed to parse " + line);
        }
    }

    public Step getStep(String key) {
        if (!steps.containsKey(key)) {
            steps.put(key, new Step(key));
        }
        return steps.get(key);
    }

    public String getSequence() {
        List<String> finishedSteps = new ArrayList<>();
        takeSteps(finishedSteps);
        return finishedSteps.stream().collect(Collectors.joining());
    }

    private void takeSteps(List<String> finishedSteps) {
        List<String> startable = steps.entrySet().stream()
                .filter(s -> !finishedSteps.contains(s.getKey()))
                .filter(entry -> entry.getValue().canStart(finishedSteps))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (!startable.isEmpty()) {
            System.out.println("Startable " + startable);
            finishedSteps.add(startable.get(0));
            takeSteps(finishedSteps);
        } else {
            System.out.println("No more startable steps");
        }
    }

    // -- Question 2 --

    public int getTimeTaken(SortedSet<Integer> workers) {
        Progress progress = new Progress(workers);
        takeSteps(progress);
        return progress.getLastStep().getTimeFinished();
    }

    private boolean finishedStepsContains(List<CompletedStep> finishedSteps, Step step) {
        return finishedSteps.stream().map(e -> e.getStep()).collect(Collectors.toList()).contains(step);
    }

    private void takeSteps(Progress progress) {
        Pair<Integer, List<Integer>> nextAvailableWorkers = progress.getNextAvailableWorkers();
        // at nextAvailableWorkers.getKey() the workers in the Set are available to perform a step.

        int time = nextAvailableWorkers.getOne();
        List<Integer> availableWorkers = nextAvailableWorkers.getTwo();
        // get next step available starting from that time
//        System.out.println("at " + time + ": workers=" + availableWorkers);
        List<Step> availableSteps = getAvailableSteps(progress, time);
        while (availableSteps.isEmpty()) {
            availableSteps = getAvailableSteps(progress, time);
            availableWorkers = progress.getAvailableWorkers(time);
            time++;
//            System.out.println("Waiting for steps to be available (time=" + time);
        }
//        System.out.println("at " + time + ": workers=" + availableWorkers + ", steps=" + availableSteps);

        // assign steps to workers
        for (int index=0; index < (Math.min(availableSteps.size(), availableWorkers.size())); index++) {
            CompletedStep cs = new CompletedStep(availableSteps.get(index), availableWorkers.get(index), time);
            progress.getFinishedSteps().add(cs);
        }

        if (progress.getFinishedSteps().size() == steps.size()) {
            System.out.println(progress.getFinishedSteps().stream().map(CompletedStep::toString).collect(Collectors.joining("\n")));
            return;
        }
        takeSteps(progress);
    }

    private List<Step> getAvailableSteps(Progress progress, Integer fromTime) {
        return steps.entrySet().stream()
                .filter(s -> !finishedStepsContains(progress.getFinishedSteps(), s.getValue()))
                .filter(entry -> entry.getValue().canStart(progress, fromTime + 1))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

//    private boolean canStart(Map.Entry<Integer, Step> entry, Progress progress) {
//        List<String> finishedStepKeys = progress.getFinishedSteps().stream()
//                .map(s -> s.getStep().getKey())
//                .collect(Collectors.toList());
//        return entry.getValue().canStart(finishedStepKeys);
//    }


}
