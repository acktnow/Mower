package com.mowitnow.mower.project.model.mower;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Possible mower directions (N,E,W,S)
 */
public enum Direction {

	NORTH("N") {
		public Direction turnLeft() { return WEST; }
		public Direction turnRight() {
			return EAST;
		}
	},
	EAST("E") {
		public Direction turnLeft() { return NORTH; }
		public Direction turnRight() {
			return SOUTH;
		}
	},
	WEST("W") {
		public Direction turnLeft() {
			return SOUTH;
		}
		public Direction turnRight() {
			return NORTH;
		}
	},
	SOUTH("S") {
		public Direction turnLeft() {
			return EAST;
		}
		public Direction turnRight() {
			return WEST;
		}
	};

    /**
     * The letter of the direction
     */
	private final String symbol;

	Direction(String symbol) {
	    this.symbol = symbol;
	}

    /**
     * The map with key = direction letter and
     * its specified value is the enum type itself
     */
    private static final Map<String, Direction> stringToEnum =
            Stream.of(values()).collect(toMap(Direction::getSymbol, e -> e));

    /**
     * Get the direction enum type from its letter
     *
     * @param symbol direction letter
     * @return the direction enum type
     */
    public static Direction fromString(String symbol) {
        return stringToEnum.get(symbol);
    }


    // ABSTRACT METHODS TO BE IMPLEMENTED BY EACH ENUM TYPE

    /**
     * Rotate counter clockwise by 90 degrees
     */
    abstract public Direction turnLeft();

    /**
     * Rotate clockwise by 90 degrees
     */
    abstract public Direction turnRight();


	// ACCESSOR METHODS

    /**
     * @return the letter of the direction
     */
    public String getSymbol() {
        return symbol;
    }
}