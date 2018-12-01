package nl.openweb.adventofcode2018.ivor.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

/**
 * @author Ivor
 */
public class Day1Question1 {


    /**
     * Counts the numbers in the file
     * @param args not used
     */
    public static void main(String... args) {

        try {
            var result = new Day1Question1().getSum();
            System.out.println("Day1 Q1 result=" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public IntStream getLines() throws IOException {
        var inputFileName = "C:\\projects\\openweb\\sourcecodes\\adventofcode2018\\Ivor\\src\\main\\resources\\input_day1.txt";
        return Files.lines(Path.of(inputFileName)).mapToInt(Integer::parseInt);
    }

    public Integer getSum() throws IOException {
        return getLines().sum();
    }
}
