package nl.openweb.adventofcode2018.ivor.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
public class Day2Question2 extends Day2Question1 {

    private List<String> codes;
    private List<BoxCategory> boxCategories = new ArrayList<>();

    public static void main(String... args) {

        // The boxes will have IDs which differ by exactly one character at the same position in both strings.
        // get common letters

        String commonLetters = new Day2Question2().getCommonLettersOfBoxes();
        System.out.println("Common letters of boxes = " + commonLetters);
    }

    private String getCommonLettersOfBoxes() {
        codes = getLines().collect(Collectors.toList());

        List<String> commonLetters = codes.stream()
                .map(this::getBoxCategory)
                .filter(BoxCategory::hasMultipleBoxes)
                .map(BoxCategory::getCommonLetters)
                .collect(Collectors.toList());

        if (commonLetters.isEmpty()) {
            System.out.println("No commonLetters found");
            return "";
        } else if(commonLetters.size() > 1) {
            System.out.println("Multiple ranges is not expected: " + commonLetters);
            return "";
        }
        return commonLetters.get(0);
    }

    private BoxCategory getBoxCategory(String code) {
        return boxCategories.stream()
                .filter(category -> category.tryBox(code))
                .findFirst()
                .orElse(addNewCategory(code));
    }

    private BoxCategory addNewCategory(String code) {
        BoxCategory boxCategory = new BoxCategory(code);
        boxCategories.add(boxCategory);
        return boxCategory;
    }

}
