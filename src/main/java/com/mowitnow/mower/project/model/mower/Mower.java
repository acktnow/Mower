package com.mowitnow.mower.project.model.mower;

import com.mowitnow.mower.project.Constants;
import com.mowitnow.mower.project.model.lawn.Lawn;

/**
 * Traditional Mower with an ID, a position defined by its coordinates x and y
 * and a direction (North, East, West or South).
 */
public abstract class Mower {

    /**
     * The id of the mower
     */
    private int id;

    /**
     * The x coordinate of the mower
     */
    private int positionX;

    /**
     * The y coordinate of the mower
     */
    private int positionY;

    /**
     * The direction of the mower
     */
	private Direction direction;

    /**
     * Constructs a traditional mower with an id, the position and the direction
     */
    protected Mower(final int id, final int positionX, final int positionY, final Direction direction) {
		this.id = id;
        this.positionX = positionX;
		this.positionY = positionY;
		this.direction = direction;
	}

    /**
     * Move the mower on the lawn
     *
     * @param lawn the lawn where the mower moves
     */
	public abstract void move(Lawn lawn);


	// OVERRIDE OBJECT METHODS

    /**
     * Returns the description about the tradition mower
     * The map is like the following :
     * "pathToFile.txt : {lawnDimensions=5 5, 1 2 N=GAGAGAGAA, 3 3 E=AADAADADDA}"
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mower #")
          .append(this.id)
          .append(" : ")
          .append(this.positionX)
          .append(Constants.SPACE_DELIMITER)
          .append(this.positionY)
          .append(Constants.SPACE_DELIMITER)
          .append(this.direction);
        return sb.toString();
    }


	// ACCESSOR AND MUTATOR METHODS

    /**
     * @return the mower id
     */
    public int getId() {
	    return this.id;
	}

    /**
     * @return the mower x coordinate
     */
    public int getPositionX() {
	    return this.positionX;
	}

    /**
     * @param positionX the mower x coordinate
     */
    public void setPositionX(int positionX) {
	    this.positionX = positionX;
	}

    /**
     * @return the mower y coordinate
     */
    public int getPositionY() {
		return this.positionY;
	}

    /**
     * @param positionY the mower y coordinate
     */
    public void setPositionY(int positionY) {
	    this.positionY = positionY;
	}

    /**
     * @return the mower direction
     */
    public Direction getDirection() {
		return this.direction;
	}

    /**
     * @param direction the mower direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}