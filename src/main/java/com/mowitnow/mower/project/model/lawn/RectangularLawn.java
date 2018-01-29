package com.mowitnow.mower.project.model.lawn;

/**
 * Lawn Rectangular Surface Model Object.
 *
 * RectangularLawn object has a width, a height and is divided into a grid.
 * The grid is used to know where objects are located and for objects retrieval and removal.
 * Position {0,0} corresponds to the bottom left of the surface
 * and {width,height} to the top right.
 */
public class RectangularLawn extends Lawn {

    /**
     * The width of the surface
     */
    private int width;

    /**
     * The height of the surface
     */
    private int height;

    /**
     * The two-dimensional array that represents the lawn divided into a grid
     */
    private int[][] lawnGrid;

    /**
     * Constructs a rectangular lawn surface with the width, the height
     * and the two-dimensional array according to the first parameters instance.
     * The lawn grid values are initialized to 0, meaning that no mower is on the lawn
     *
     * @param  width the lawn width
     * @param  height the lawn height
     */
    public RectangularLawn(final int width, final int height) {
        this.width = width;
        this.height = height;
        lawnGrid = new int[width+1][height+1];
    }


    // OVERRIDE LAWN METHODS

    /**
     * This is an implementation of the {@link Lawn#contains(int, int)} method.
     */
    @Override
    public boolean contains(int x, int y) {
        if (x >= 0 && x <= width && y >= 0 && y <= height) return true;
        return false;
    }

    /**
     * This is an implementation of the {@link Lawn#isOccupied(int, int)} method.
     * The lawn is considered as occupied when the value at position {x,y} is 0.
     */
    @Override
    public boolean isOccupied(int x, int y) {
        if (lawnGrid[x][y] != 0) {
            return true;
        }
        return false;
    }

    /**
     * This is an implementation of the {@link Lawn#placeMower(int, int, int)} method.
     * The id corresponds to the mower identifier/number.
     */
    @Override
    public void placeMower(int id, int x, int y) {
        lawnGrid[x][y] = id;
    }

    /**
     * This is an implementation of the {@link Lawn#removeMower(int, int)} method.
     */
    @Override
    public void removeMower(int x, int y) {
        lawnGrid[x][y] = 0;
    }


    // OVERRIDE OBJECT METHODS

    /**
     * Returns the values of the width and the height of the lawn.
     * [5,5]
     */
    @Override
    public String toString() {
        return "[" + this.width + "," + this.height + "]";
    }


    // ACCESSOR METHODS

    /**
     * @return the lawn width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the lawn height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return grid representing the lawn
     */
    public int[][] getLawnGrid() {
        return lawnGrid;
    }
}