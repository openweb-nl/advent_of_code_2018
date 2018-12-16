package com.aharpour.adventofcode;

import java.util.function.IntBinaryOperator;

public class Day16 {

    private int[] registers = new int[4];

    private Opcode[] opcodes = new Opcode[]{
            new OpcodeRR((a, b) -> a + b),
            new OpcodeRI((a, b) -> a + b),
            new OpcodeRR((a, b) -> a * b),
            new OpcodeRI((a, b) -> a * b),
            new OpcodeRR((a, b) -> a & b),
            new OpcodeRI((a, b) -> a & b),
            new OpcodeRR((a, b) -> a | b),
            new OpcodeRI((a, b) -> a | b),
            new OpcodeRR((a, b) -> a),
            new OpcodeIR((a, b) -> a),

            new OpcodeIR((a, b) -> a > b ? 1 : 0),
            new OpcodeRI((a, b) -> a > b ? 1 : 0),
            new OpcodeRR((a, b) -> a > b ? 1 : 0),
            new OpcodeIR((a, b) -> a == b ? 1 : 0),
            new OpcodeRI((a, b) -> a == b ? 1 : 0),
            new OpcodeRR((a, b) -> a == b ? 1 : 0),
    };


    public void applyOpcode(int i, int a, int b, int c) {
        opcodes[i].run(a, b, c);
    }

    public void setRegisters(int[] registers) {
        if (registers.length != 4) {
            throw new IllegalArgumentException();
        }
        this.registers = registers;
    }

    public int[] getRegisters() {
        return registers;
    }

    public void setRegisters(int r1, int r2, int r3, int r4) {
        registers[0] = r1;
        registers[1] = r2;
        registers[2] = r3;
        registers[3] = r4;
    }

    public boolean isRegistersSetTo(int r1, int r2, int r3, int r4) {
        return registers[0] == r1 &&
                registers[1] == r2 &&
                registers[2] == r3 &&
                registers[3] == r4;
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
            if (i > 3 || i < 0) {
                throw new IllegalArgumentException();
            }
            return registers[i];
        }

        public void setValueToRegister(int i, int value) {
            if (i > 3 || i < 0) {
                throw new IllegalArgumentException();
            }
            registers[i] = value;
        }
    }


}
