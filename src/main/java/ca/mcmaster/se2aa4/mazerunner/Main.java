package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;


public class Main {
    // mvp version can only pass on the straight maze as it only goes forward.
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        logger.warn("This implementation only works for a straight maze!");
        InputHandler inputHandler = new InputHandler();

        if (!inputHandler.parseArgs(args)) {
            logger.error("Failed to parse command-line arguments.");
            return;
        }

        String inputFilePath = inputHandler.getInputFilePath();
        if (inputFilePath == null) {
            logger.error("Failed to read File Path.");
            return;
        }

        try {
            Maze maze = new Maze(inputFilePath);
            Explorer explorer = new Explorer(maze); // Create the explorer

            logger.info("**** Computing path");
            explorer.exploreMaze();

        } catch(Exception e) {
            logger.error("/!\\ An error has occurred /!\\ Error:{}", e.getMessage());
        }
        //logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}