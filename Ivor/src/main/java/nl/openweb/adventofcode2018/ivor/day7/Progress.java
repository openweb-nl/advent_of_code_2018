package nl.openweb.adventofcode2018.ivor.day7;

import nl.openweb.adventofcode2018.ivor.day4.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
public class Progress {
    private List<CompletedStep> finishedSteps = new ArrayList<>();
    private SortedSet<Integer> workers;


    public Progress(SortedSet<Integer> workers) {
        this.workers = workers;
    }

    public List<CompletedStep> getFinishedSteps() {
        return finishedSteps;
    }



    public List<Integer> getAvailableWorkers(int time) {
        return workers.stream().filter(worker -> isWorkerAvailable(worker, time)).collect(Collectors.toList());
    }

    private boolean isWorkerAvailable(Integer worker, int time) {
        return finishedSteps.stream()
                .noneMatch(cs -> (cs.getWorker() == worker && cs.getTimeFinished() > (time+1)));
    }


    public Pair<Integer, List<Integer>> getNextAvailableWorkers() {
        // return the time the next available worker(s) are available
        Map<Integer, Integer> workerAvailableAtTime = new HashMap<>();
        for (Integer worker : workers) {
            OptionalInt max = finishedSteps.stream().filter(cs -> cs.getWorker() == worker).mapToInt(CompletedStep::getTimeFinished).max();
            workerAvailableAtTime.put(worker, max.orElse(0));
        }

        Optional<Integer> minTime = workerAvailableAtTime.entrySet().stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getValue);

        if (minTime.isPresent()) {
            List<Integer> workers = workerAvailableAtTime.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(minTime.get()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            workers.sort(Integer::compareTo);
            return new Pair<>(minTime.get(), workers);
        }
        System.out.println("Always expect a next worker to be available");
        return null;
    }

    public CompletedStep getLastStep() {
        return finishedSteps.stream().max(Comparator.comparingInt(CompletedStep::getTimeFinished)).get();
    }

}
