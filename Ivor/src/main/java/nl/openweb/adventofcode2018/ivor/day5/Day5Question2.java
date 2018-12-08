package nl.openweb.adventofcode2018.ivor.day5;

import nl.openweb.adventofcode2018.ivor.day4.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ivor
 */
public class Day5Question2 extends Day5Question1 {

    public static void main(String... args) {
        Day5Question2 q2 = new Day5Question2();
        System.out.println("Answer: " + q2.getAnswer().length());
    }

    public String getAnswer() {
        return getAnswerMap().entrySet().stream()
                .min((entry1, entry2) -> entry1.getValue().length() > entry2.getValue().length() ? 1 : -1)
                .get()
                .getValue();
    }

    public Map<String, String> getAnswerMap() {
        Map<String, String> result = new HashMap<>();
        String line = getLine();
        for (char c : ALPHABET) {
            String key = String.valueOf(c);
            System.out.println("key: " + key);
            String regex = "[" + key + key.toUpperCase() + "]";
            String testLine = line.replaceAll(regex, "");
//            testLine = testLine.replaceAll(key.toUpperCase(), "");
            replaceOpposites(testLine).ifPresent(it -> result.put(key, it));
        }
        System.out.println("map: " + result );
        return result;
    }

    public Optional<String> replaceOpposites(String key, String input) {
        String originalLine = input;
        input = input.replaceAll(key, "");
        System.out.println("result=" + input + " from " + originalLine + " by replacing " + key);
        if (originalLine.length() == input.length() ) {
            return Optional.of(input);
        }
        return replaceOpposites(key, input);
    }
}
