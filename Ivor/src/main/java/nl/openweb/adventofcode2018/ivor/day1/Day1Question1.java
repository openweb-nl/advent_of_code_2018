package nl.openweb.adventofcode2018.ivor.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            var result = new Day1Question1().getLines().sum();
            System.out.println("Day1 Q1 result=" + result);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }


    public IntStream getLines() throws IOException, URISyntaxException {
        var inputFileName = "input_day1.txt";
        return Files.lines( Paths.get(getClass().getClassLoader()
                .getResource(inputFileName).toURI())).mapToInt(Integer::parseInt);
    }

}
