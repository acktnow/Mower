package com.mowitnow.mower.project.model.mower;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Possible mower instructions (G,D,A)
 */
public enum Instruction {

    TURN_LEFT('G'),
    TURN_RIGHT('D'),
    MOVE_FORWARD('A');

    /**
     * The letter of the instruction
     */
    private final char symbol;

    Instruction(char symbol) {
        this.symbol = symbol;
    }

    /**
     * The map with key = instruction letter and
     * its specified value is the enum type itself
     */
    private static final Map<Character, Instruction> charToEnum =
            Stream.of(values()).collect(toMap(Instruction::getSymbol, e -> e));

    /**
     * Get the instruction enum type from its letter
     *
     * @param symbol instruction letter
     * @return the instruction enum type
     */
    public static Instruction fromChar(char symbol) {
        return charToEnum.get(symbol);
    }


    // ACCESSOR METHODS

    /**
     * @return the letter of the instruction
     */
    public char getSymbol() {
        return symbol;
    }
}