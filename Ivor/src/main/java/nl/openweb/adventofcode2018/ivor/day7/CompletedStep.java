package nl.openweb.adventofcode2018.ivor.day7;

/**
 * @author Ivor
 */
public class CompletedStep {
    public static final int FIXEDTIME = 60;
    private final Step step;
    private final int worker;
    private final int startTime;


    public CompletedStep(Step step, int worker, int startTime) {
        this.step = step;
        this.worker = worker;
        this.startTime = startTime;
    }

    public int getWorker() {
        return worker;
    }

    public Step getStep() {
        return step;
    }

    public int getTimeTaken() {
        return FIXEDTIME + (step.getKey().chars().sum() - 64);
    }

    public int getTimeFinished() {
        return startTime + getTimeTaken();
    }

    @Override
    public String toString() {
        return step.toString() + " was done by worker " + worker + ", was started at " + startTime + " and finished at " + getTimeFinished();
    }
}
