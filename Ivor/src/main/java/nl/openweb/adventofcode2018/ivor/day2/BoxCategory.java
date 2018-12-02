package nl.openweb.adventofcode2018.ivor.day2;

import java.util.*;

/**
 * @author Ivor
 */
public class BoxCategory {
    private List<String> codes = new ArrayList<>();
    private int indexOfVariableLetter = -1;

    public BoxCategory(String code) {
        this.codes.add(code);
    }

    public boolean tryBox(String code) {
        if (codes.isEmpty() || hasOnlyDifferenceAtIndex(code)) {
            codes.add(code);
            return true;
        }
        return false;
    }

    private boolean hasOnlyDifferenceAtIndex(String code) {
        char[] charsFirst = codes.get(0).toCharArray();
        char[] charsSecond = code.toCharArray();
        if (charsFirst.length != charsSecond.length) {
            System.out.println("Codes differ in length " + code + " and " + codes.get(0));
            return false;
        }

        int diffCount = 0;
        int diffIndex = -1;
        for (int index = 0; index < charsFirst.length; index++) {
            if (charsFirst[index] != charsSecond[index]) {
                diffIndex = index;
                diffCount++;
            }
            if (diffCount > 1 || (diffCount == 1 && diffIndex != indexOfVariableLetter && indexOfVariableLetter > -1)) {
                return false;
            }
        }
        indexOfVariableLetter = diffIndex;
        return true;
    }

    public boolean hasMultipleBoxes() {
        return codes.size() > 1;
    }

    public String getCommonLetters() {
        if (indexOfVariableLetter < 0) {
            return "";
        }
        String code = codes.get(0);
        return code.substring(0, indexOfVariableLetter) + code.substring(indexOfVariableLetter + 1);
    }

    public List<String> getCodes() {
        return Collections.unmodifiableList(codes);
    }

    public int getIndexOfVariableLetter() {
        return indexOfVariableLetter;
    }

    @Override
    public String toString() {
        return "boxCategory varIndex=" + indexOfVariableLetter + ", codes: " + codes;
    }
}
