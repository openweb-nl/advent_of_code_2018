package com.aharpour.adventofcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class Day19 {

    private int[] registers = new int[6];

    private Map<String, Opcode> opcodes = new HashMap<>();
    private int instructionPointer;
    private Instruction[] instructions;


    public Day19(String input) {
        setOpcodes();
        parseInput(input);
    }

    public void run() {
        int length = instructions.length;
        while (registers[instructionPointer] < length) {
            int instructionIndex = registers[instructionPointer];
            applyOptimization(instructionIndex);
            applyInstruction(instructions[instructionIndex]);
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
