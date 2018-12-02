package day02;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import util.FilesUtil;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Checksum {

    public static void main(String[] args) {
        part1();
        part2();
    }

    static void part1() {
        List<String> input = FilesUtil.read("mxdrenthe/day02/input.txt");
        final AtomicInteger twice = new AtomicInteger();
        final AtomicInteger three = new AtomicInteger();
        input.stream()
                .map(line -> expand(line).stream()
                        .collect(groupingBy(
                                letter -> letter,
                                counting()
                        )))
                .forEach(entry -> {
                            if (entry.values().contains(2L)) {
                                twice.incrementAndGet();
                            }
                            if (entry.values().contains(3L)) {
                                three.incrementAndGet();
                            }
                        }
                );
        System.out.println(twice.get() * three.get());
    }

    static void part2() {
        List<String> input = FilesUtil.read("mxdrenthe/day02/input.txt");

        String word1 = "";
        String word2 = "";
        int same = 0;

        for (int outer = 0; outer < input.size(); outer++) {
            String outerWord = input.get(outer);
            for (int inner = 0; inner < input.size(); inner++) {
                String innerWord = input.get(inner);
                if (outer != inner) {
                    int compared = compare(outerWord, innerWord);
                    if (same < compared) {
                        word1 = outerWord;
                        word2 = innerWord;
                        same = compared;
                    }
                }
            }
        }

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                System.out.print(word1.charAt(i));
            }
        }
    }

    static int compare(String word1, String word2) {
        int amount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) == word2.charAt(i)) {
                amount++;
            }
        }
        return amount;
    }

    static List<String> expand(String s) {
        return s.codePoints()
                .mapToObj(codePoint -> Character.toString((char) codePoint))
                .collect(toList());
    }
}
