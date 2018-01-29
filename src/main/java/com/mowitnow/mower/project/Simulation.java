package com.mowitnow.mower.project;

import com.mowitnow.mower.project.model.lawn.Lawn;
import com.mowitnow.mower.project.model.lawn.RectangularLawn;
import com.mowitnow.mower.project.model.mower.AutomaticMower;
import com.mowitnow.mower.project.model.mower.Direction;
import com.mowitnow.mower.project.model.mower.Mower;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This class instantiates the lawn as well as the mowers in a list.
 * After initialization, the simulation can start.
 */
public class Simulation {

    private final static Logger logger = Logger.getLogger(Simulation.class);

    /**
     * The lawn
     */
    private Lawn lawn;

    /**
     * The mower list
     */
    private List<Mower> mowerList;

    /**
     * Constructs the simulation instance with the data parsed from the file.
     *
     * @param parsedDataMap the data parsed
     */
    public Simulation(Map<String, String> parsedDataMap) {
        logger.info("Initializing lawn...");
        lawn = initializeLawn(parsedDataMap);
        logger.info("Lawn initialized : " + lawn);
        logger.info("Initializing mower list...");
        mowerList = initializeMower(parsedDataMap);
        logger.info("List of mowers initialized : " + mowerList);
    }

    /**
     * Start the simulation
     */
    public void start() {
        logger.info("Placing the different mowers on the lawn...");
        mowerList.stream().forEach((mower) ->
                lawn.placeMower(mower.getId(), mower.getPositionX(), mower.getPositionY()));
        logger.info("All mowers have been placed on the lawn.");
        logger.info("Start movement of the mowers !");
        mowerList.stream().forEach((mower) -> mower.move(lawn));
        logger.info("All mowers have arrived at destination.");
    }

    /**
     * Initializes the lawn with its dimensions
     *
     * @param parsedDataMap the data parsed
     */
    private Lawn initializeLawn(Map<String, String> parsedDataMap) {
        String[] lawnDimensions = parsedDataMap.get(Constants.LAWN_DIMENSIONS_KEY)
                .split(Constants.SPACE_DELIMITER);
        int width = Integer.parseInt(lawnDimensions[0]);
        int height = Integer.parseInt(lawnDimensions[1]);
        return new RectangularLawn(width, height);
    }

    /**
     * Initializes a list of mowers with their ids, positions and instruction sequences
     *
     * @param parsedDataMap the data parsed
     */
    private List<Mower> initializeMower(Map<String, String> parsedDataMap) {
        mowerList = new ArrayList<>();
        // Skip the first line of the map in order to only have mower data
        Map<String,String> mowerDataMap = parsedDataMap.entrySet().stream()
                .skip(1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Format the data to instantiate each automatic mower with its characteristics
        AtomicInteger atomicInteger = new AtomicInteger(0);
        mowerDataMap.forEach((k,v) -> {
            String[] mowerPosition = k.split(Constants.SPACE_DELIMITER);
            int positionX = Integer.parseInt(mowerPosition[0]);
            int positionY = Integer.parseInt(mowerPosition[1]);
            // filter the mowers that initially are out of bounds
            if (!lawn.contains(positionX, positionY)) {
                logger.info("The mower at position x=" + positionX + " and y=" + positionY + " is out of bounds. " +
                        "Do not add to the list.");
            } else {
                String directionString = mowerPosition[2];
                String instructionSequence = v;
                mowerList.add(new AutomaticMower(atomicInteger.incrementAndGet(), positionX, positionY,
                        Direction.fromString(directionString), instructionSequence));
            }
        });
        return mowerList;
    }
}
