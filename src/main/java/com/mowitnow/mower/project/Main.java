package com.mowitnow.mower.project;

import com.mowitnow.mower.project.parser.FileParser;
import org.apache.log4j.Logger;


public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    /**
     * The file given by MowItNow
     */
    //public static final String fileName = "src/main/resources/dataFile.txt";

    /**
     * The file with examples of mower collision
     */
    //public static final String fileName = "src/main/resources/dataFileWithCollision.txt";

    public static void main(String[] args) {
        logger.info("Application is starting...");
		FileParser parser = new FileParser(args[0]);
		if (parser.parse()) {
		    Simulation simulation = new Simulation(parser.getParsedDataMap());
            simulation.start();
        } else {
            logger.info("The content of the file is not valid.");
        }
        logger.info("Application is ending...");
    }

}
