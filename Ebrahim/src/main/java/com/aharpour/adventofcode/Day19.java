package com.aharpour.adventofcode;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class Day19 {

    private int[] registers = new int[6];

    private Map<String, Opcode> opcodes = new HashMap<>();
    private int instructionPointer;
    private Instruction[] instructions;
    private boolean verbose = false;
    protected PrintStream out = System.out;


    public Day19(String input) {
        setOpcodes();
        parseInput(input);
    }

    public void run() {
        int length = instructions.length;
        while (registers[instructionPointer] < length) {
            int instructionIndex = registers[instructionPointer];
            applyOptimization(instructionIndex);
            if (verbose) {
                printResiters();
                printInstruction(instructionIndex);
            }
            applyInstruction(instructions[instructionIndex]);
        }

    }

    protected void printResiters() {
        out.println("r0=" + registers[0] + " r1=" + registers[1] + " r2=" + registers[2] + " r3=" + registers[3]
                + " r4=" + registers[4] + " r5=" + registers[5]);
    }

    protected void printInstruction(int instructionIndex) {
        Instruction instruction = instructions[instructionIndex];
        Opcode opcode = opcodes.get(instruction.name);
        String a;
        String b;
        if (opcode instanceof OpcodeRR) {
            a = "r" + instruction.a;
            b = "r" + instruction.b;
        } else if (opcode instanceof OpcodeRI) {
            a = "r" + instruction.a;
            b = Integer.toString(instruction.b);
        } else {
            a = Integer.toString(instruction.a);
            b = "r" + instruction.b;
        }
        if (instruction.name.startsWith("set")) {
            out.println("r" + instruction.c + " = " + a);
        } else {
            out.println("r" + instruction.c + " = " + a + " " + getSymbol(instruction.name) + " " + b);
        }
    }

    private String getSymbol(String name) {
        String symbol = "";
        if (name.startsWith("add")) {
            symbol = "+";
        } else if (name.startsWith("mul")) {
            symbol = "*";
        } else if (name.startsWith("bor")) {
            symbol = "|";
        } else if (name.startsWith("eq")) {
            symbol = "==";
        } else if (name.startsWith("gt")) {
            symbol = ">";
        } else if (name.startsWith("ban")) {
            symbol = "&";
        }
        return symbol;
    }

    public void printInstructions() {
        for (int i = 0; i < instructions.length; i++) {
            printInstruction(i);
        }
    }

    public void applyOptimization(int instructionIndex) {

    }

    private void applyInstruction(Instruction instruction) {
        opcodes.get(instruction.name).run(instruction.a, instruction.b, instruction.c);
        registers[instructionPointer] += 1;
    }

    private void parseInput(String input) {
        String[] rows = input.split("\\s*\\n\\s*");
        instructionPointer = Integer.parseInt(rows[0].substring("#ip ".length()));
        instructions = new Instruction[rows.length - 1];
        for (int i = 1; i < rows.length; i++) {
            instructions[i - 1] = rowToInstruction(rows[i]);
        }
    }

    private Instruction rowToInstruction(String row) {
        String[] split = row.split("\\s");
        return new Instruction(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
    }

    protected void setRegisters(int[] registers) {
        if (registers.length != 6) {
            throw new IllegalArgumentException();
        }
        this.registers = registers;
    }

    protected int[] getRegisters() {
        return registers;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    private class OpcodeIR extends Opcode {
        private OpcodeIR(IntBinaryOperator operator) {
            super(operator);
        }

        @Override
        public void run(int a, int b, int c) {
            setValueToRegister(c, operator.applyAsInt(a, valueFromRegister(b)));
        }
    }

    private class OpcodeRI extends Opcode {
        private OpcodeRI(IntBinaryOperator operator) {
            super(operator);
        }

        @Override
        public void run(int a, int b, int c) {
            setValueToRegister(c, operator.applyAsInt(valueFromRegister(a), b));
        }
    }

    private class OpcodeRR extends Opcode {

        private OpcodeRR(IntBinaryOperator operator) {
            super(operator);
        }

        @Override
        public void run(int a, int b, int c) {
            setValueToRegister(c, operator.applyAsInt(valueFromRegister(a), valueFromRegister(b)));
        }
    }

    private abstract class Opcode {
        protected final IntBinaryOperator operator;

        protected Opcode(IntBinaryOperator operator) {
            this.operator = operator;
        }

        public abstract void run(int a, int b, int c);


        public int valueFromRegister(int i) {
            if (i > registers.length - 1 || i < 0) {
                return 0;
            }
            return registers[i];
        }

        public void setValueToRegister(int i, int value) {
            if (i > registers.length - 1 || i < 0) {
                throw new IllegalArgumentException();
            }
            registers[i] = value;
        }
    }

    @Data
    @AllArgsConstructor
    @ToString(doNotUseGetters = true, includeFieldNames = false)
    private class Instruction {
        private String name;
        private int a;
        private int b;
        private int c;
    }

    private void setOpcodes() {
        opcodes.put("addr", new OpcodeRR((a, b) -> a + b));
        opcodes.put("addi", new OpcodeRI((a, b) -> a + b));
        opcodes.put("mulr", new OpcodeRR((a, b) -> a * b));
        opcodes.put("muli", new OpcodeRI((a, b) -> a * b));
        opcodes.put("banr", new OpcodeRR((a, b) -> a & b));
        opcodes.put("bani", new OpcodeRI((a, b) -> a & b));
        opcodes.put("borr", new OpcodeRR((a, b) -> a | b));
        opcodes.put("bori", new OpcodeRI((a, b) -> a | b));
        opcodes.put("setr", new OpcodeRR((a, b) -> a));
        opcodes.put("seti", new OpcodeIR((a, b) -> a));
        opcodes.put("gtir", new OpcodeIR((a, b) -> a > b ? 1 : 0));
        opcodes.put("gtri", new OpcodeRI((a, b) -> a > b ? 1 : 0));
        opcodes.put("gtrr", new OpcodeRR((a, b) -> a > b ? 1 : 0));
        opcodes.put("eqir", new OpcodeIR((a, b) -> a == b ? 1 : 0));
        opcodes.put("eqri", new OpcodeRI((a, b) -> a == b ? 1 : 0));
        opcodes.put("eqrr", new OpcodeRR((a, b) -> a == b ? 1 : 0));
    }


}
