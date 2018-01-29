package com.mowitnow.mower.project.model.mower;

import com.mowitnow.mower.project.model.lawn.Lawn;
import org.apache.log4j.Logger;

/**
 * Automatic Mower Model Object.
 *
 * AutomaticMower object has an id, a position defined by its coordinates x and y,
 * a direction and an instruction sequence that controls it.
 *
 */
public class AutomaticMower extends Mower {

    private final static Logger logger = Logger.getLogger(AutomaticMower.class);

    /**
     * The mower instruction sequence
     */
    private String instructionSequence;

    /**
     * Constructs an automatic mower with an id, the position and the direction and
     * its instruction sequence
     *
     * @param id the mower id
     * @param positionX the mower coordinate x
     * @param positionY the mower coordinate y
     * @param direction the mower direction
     * @param instructionSequence the mower instruction sequence
     */
    public AutomaticMower(int id, int positionX, int positionY,
                          Direction direction, String instructionSequence) {
        super(id, positionX, positionY, direction);
        this.instructionSequence = instructionSequence;
    }


    // OVERRIDE MOWER METHOD

    /**
     * This is an implementation of the {@link Mower#move(Lawn)} method.
     * The mower moves can turn left, right or move forward
     * according to its instruction sequence.
     * The mower reaches its destination if only it stays on the lawn
     * and there is not another mower at the destination position.
     */
    @Override
    public void move(Lawn lawn) {
        if(logger.isDebugEnabled()){
            logger.debug(this);
        }
        String instructionSequence = getInstructionSequence();
        for (char instruction : instructionSequence.toCharArray()) {
            switch (Instruction.fromChar(instruction)) {
                case TURN_LEFT:
                    setDirection(getDirection().turnLeft());
                    break;
                case TURN_RIGHT:
                    setDirection(getDirection().turnRight());
                    break;
                case MOVE_FORWARD:
                    int x = getPositionX();
                    int y = getPositionY();
                    int currentPositionX = x;
                    int currentPositionY = y;
                    switch (getDirection()) {
                        case NORTH: y++; break;
                        case EAST: x++; break;
                        case WEST: x--; break;
                        case SOUTH: y--; break;
                        default:
                            logger.error("Unknown mower direction");
                            throw new AssertionError("Unknown direction");
                    }
                    if (lawn.contains(x,y)) {
                        if (!lawn.isOccupied(x,y)) {
                            setPositionX(x);
                            setPositionY(y);
                            lawn.removeMower(currentPositionX, currentPositionY);
                            lawn.placeMower(this.getId(), x, y);
                        } else {
                            if(logger.isDebugEnabled()) {
                                logger.debug("Cannot move forward " +
                                        "because another mower is already at position {" + x + "," + y + "}");
                            }
                        }
                    } else {
                        if(logger.isDebugEnabled()) {
                            logger.debug("Cannot move forward" +
                                    "because the mower is going out of bounds : {" + x + "," + y + "} ");
                        }
                    }
                    break;
                default:
                    logger.error("Unknown instruction given to the mower.");
                    throw new AssertionError("Unknown instruction");
            }
            if(logger.isDebugEnabled()){
                logger.debug(this);
            }
        }
        logger.info("The final destination of this mower is " + this);
    }


    // OVERRIDE OBJECT METHODS

    /**
     * Returns the description about the tradition mower
     * The map is like the following :
     * "pathToFile.txt : {lawnDimensions=5 5, 1 2 N=GAGAGAGAA, 3 3 E=AADAADADDA}"
     */
    @Override
    public String toString() {
        return super.toString() + " - " + this.instructionSequence;
    }


    // ACCESSOR METHODS

    /**
     * @return the mower instruction sequence
     */
    public String getInstructionSequence() {
        return this.instructionSequence;
    }

}
