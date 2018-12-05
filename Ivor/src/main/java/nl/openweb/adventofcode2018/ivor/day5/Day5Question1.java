package nl.openweb.adventofcode2018.ivor.day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Ivor
 */
public class Day5Question1 {
    public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public String getLine() {
        var inputFileName = "input_day5.txt";
        try {
            return Files.lines(Paths.get(getClass().getClassLoader()
                    .getResource(inputFileName).toURI())).findFirst().orElse("");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    public Optional<String> replaceOpposites(String input) {
        String originalLine = input;
        // replace All aA etc with ""
        for (char c : ALPHABET) {
            input = input.replaceAll(c + String.valueOf(c).toUpperCase(), "");
            input = input.replaceAll(String.valueOf(c).toUpperCase() + c, "");
        }

        if (originalLine.length() == input.length() ) {
            return Optional.of(input);
        }
        return replaceOpposites(input);
    }

    public static void main(String... args) {
        Day5Question1 day5Question1 = new Day5Question1();
        Optional<String> value = day5Question1.replaceOpposites(day5Question1.getLine());
        value.ifPresent(s -> System.out.println("Remaining length " + s.length() + " --> " + s));
        System.out.println("DONE");
    }

}
