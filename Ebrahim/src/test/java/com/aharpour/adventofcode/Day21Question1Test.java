package com.aharpour.adventofcode;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

/**
 * @author Ebrahim Aharpour
 * @since 12/21/2018
 */
public class Day21Question1Test {

    @Test
    public void realCase() throws FileNotFoundException {
        Day21Question1 question = new Day21Question1("#ip 3\n" +
                "seti 123 0 4\n" +
                "bani 4 456 4\n" +
                "eqri 4 72 4\n" +
                "addr 4 3 3\n" +
                "seti 0 0 3\n" +
                "seti 0 6 4\n" +
                "bori 4 65536 1\n" +
                "seti 678134 1 4\n" +
                "bani 1 255 5\n" +
                "addr 4 5 4\n" +
                "bani 4 16777215 4\n" +
                "muli 4 65899 4\n" +
                "bani 4 16777215 4\n" +
                "gtir 256 1 5\n" +
                "addr 5 3 3\n" +
                "addi 3 1 3\n" +
                "seti 27 8 3\n" +
                "seti 0 1 5\n" +
                "addi 5 1 2\n" +
                "muli 2 256 2\n" +
                "gtrr 2 1 2\n" +
                "addr 2 3 3\n" +
                "addi 3 1 3\n" +
                "seti 25 7 3\n" +
                "addi 5 1 5\n" +
                "seti 17 1 3\n" +
                "setr 5 3 1\n" +
                "seti 7 8 3\n" +
                "eqrr 4 0 5\n" +
                "addr 5 3 3\n" +
                "seti 5 4 3");
        question.setVerbose(false);
        try (PrintStream printStream = new PrintStream("output.txt")) {
            question.setOut(printStream);
            // question.printInstructions();
            printStream.println();
            int calculate = question.calculate();
        }
    }

}