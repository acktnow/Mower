package com.mowitnow.mower.project.parser;

import com.mowitnow.mower.project.Constants;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  A parser to read a file, verify its content and parse the data into a map.
 */
public class FileParser {

    private final static Logger logger = Logger.getLogger(FileParser.class);

    /**
     * The name of the file to read.
     */
    private final String fileName;

    /**
     * The data parsed from the file into a map.
     */
    private Map<String, String> parsedDataMap;

    /**
     * Constructs a file parser instance with the name of the file
     * and an empty LinkedHashMap instance.
     *
     * @param  fileName the name of the file
     */
    public FileParser(final String fileName) {
        this.fileName = fileName;
        this.parsedDataMap = new LinkedHashMap<>();
    }

    /**
     * Read the file, check if its content is valid and add the parsed data to the map
     */
    public boolean parse() {
        logger.info("Starting to parse the file : " + this.fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line = br.readLine();
            if (isLawnDimensionsValid(line)) {
                this.parsedDataMap.put(Constants.LAWN_DIMENSIONS_KEY, line);
                while ((line = br.readLine()) != null) {
                    String instructionSequence = br.readLine();
                    if (isMowerPositionValid(line)
                            && (isInstructionSequenceValid(instructionSequence))) {
                        this.parsedDataMap.put(line, instructionSequence);
                    } else {
                        if (logger.isDebugEnabled()) {
                            logger.debug("The mower data (position and/or instructions) are not valid : " + line);
                        }
                        return false;
                    }
                }
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("The lawn dimensions data are not valid : " + line);
                }
                return false;
            }
        } catch (IOException e) {
            logger.error("Error " + e + " encountered while parsing the file " + this.fileName);
        }
        logger.info("The file has been correctly parsed " + this);
        return true;
    }

    /**
     * Checks if the lawn's width and height are correctly provided
     *
     * @param  line the line of the file to parse
     */
    public boolean isLawnDimensionsValid(String line) {
        return line.matches("\\d+ \\d+");
    }

    /**
     * Checks if the mower's position and direction are correctly provided
     *
     * @param  line the line of the file to parse
     */
    public boolean isMowerPositionValid(String line) {
        return line.matches("\\d+ \\d+ [NEWS]");
    }

    /**
     * Checks if the instruction sequence is correctly provided
     *
     * @param  line the line of the file to parse
     */
    public boolean isInstructionSequenceValid(String line) {
        return line.matches("[GAD]+");
    }


    // OVERRIDE OBJECT METHODS

    /**
     * Returns the file name and the data parsed from the file.
     * The map is like the following :
     * "pathToFile.txt : {lawnDimensions=5 5, 1 2 N=GAGAGAGAA, 3 3 E=AADAADADDA}"
     */
    @Override
    public String toString() {
        return this.fileName + " : " + this.parsedDataMap;
    }


    // ACCESSOR METHODS

    /**
     * @return map containing parsed data from the file
     */
    public Map<String, String> getParsedDataMap() {
        return parsedDataMap;
    }
}
