package nl.openweb.adventofcode2018.ivor.day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Ivor
 */
public class Day4Question1 {

    public static void main(String... args) {
        GuardSchedule guardSchedule = new Day4Question1().getGuardSchedule();

        Integer id = guardSchedule.getGuardWithMostMinutesAsleep();
        int answer = id * guardSchedule.getMinuteMostAsleep(id).getOne();
        System.out.println("Answer t q1: " + answer);
    }

    GuardSchedule getGuardSchedule() {
        GuardSchedule schedule = new GuardSchedule();
        getLines().forEach(schedule::addLogLine);
        return schedule;
    }

    public Stream<String> getLines() {
        var inputFileName = "input_day4.txt";
        try {
            return Files.lines(Paths.get(getClass().getClassLoader()
                    .getResource(inputFileName).toURI())).sorted();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

}
