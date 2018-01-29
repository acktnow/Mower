package com.mowitnow.mower.project.model.lawn;

/**
 * Lawn Surface.
 */
public abstract class Lawn {

    /**
     * Checks whether the object is out of bounds
     *
     * @param x the coordinate x of the object
     * @param y the coordinate y of the object
     * @return <tt>true</tt> if the lawn contains the object which position
     * is defined by its coordinates x and y
     */
    public abstract boolean contains(int x, int y);

    /**
     * Checks whether the lawn is already occupied by an object
     * at the position {x,y}
     *
     * @param x the coordinate x of the object
     * @param y the coordinate y of the object
     * @return <tt>true</tt>  if there is an object on
     * the lawn at position {x,y}
     */
    public abstract boolean isOccupied(int x, int y);

    /**
     * Set the mower position at {x,y} on the lawn
     *
     * @param id the id of the object
     * @param x the coordinate x of the mower
     * @param y the coordinate y of the mower
     */
    public abstract void placeMower(int id, int x, int y);

    /**
     * Remove the mower located at position {x,y}
     *
     * @param x the coordinate x of the mower
     * @param y the coordinate y of the mower
     * @return the number of elements in this list
     */
    public abstract void removeMower(int x, int y);
}
