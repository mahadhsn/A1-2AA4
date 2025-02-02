package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// main class to run maze runner
public class Main {

    private static final Logger logger = LogManager.getLogger();

    // log statements turned off. only on for debugging
    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        System.out.println("Starting Maze Runner");

        InputHandler inputHandler = new InputHandler(); // initiate inputHandler

        if (!inputHandler.parseArgs(args)) { // parse arguments
            logger.error("Failed to parse command-line arguments.");
            return;
        }

        String inputFilePath = inputHandler.getInputFilePath(); // initiate inputFilePath
        String inputPath = inputHandler.getMazePath(); // initiate inputPath

        if (inputFilePath == null) { // exception case for inputFilePath
            logger.error("Failed to read File Path.");
            return;
        }

        Maze maze = new Maze(inputFilePath); // initiate maze
        Explorer explorer = new Explorer(maze); // initiate explorer

        if (inputPath == null) { // exception case for inputPath
            logger.info("No path provided.");
        }

        else { // if path is provided

            System.out.println("Solving maze with path"); 
            System.out.println("Starting maze at: " + Arrays.toString(maze.getLeftOpening())); // print starting position
            Path path = new Path(inputPath); // initiate path

            System.out.println("Inputted canonical path: " + path.getFormattedInputtedPath());
            System.out.println("Inputted factorized path: " + path.getFactorizedInputtedPath());

            MazeValidator mazeValidator = new MazeValidator(maze, explorer, path); // initiate mazeValidator
            if (mazeValidator.getIsValid()) { 
                System.out.println("Maze solved successfully with inputted path."); // print if maze is solved
            }
            else {
                System.out.println("Maze not solved with inputted path!"); // print if maze is not solved
                System.out.println("Maze runner stopped at: " + Arrays.toString(explorer.getCurrentPosition())); // print where maze runner stopped
            }
            return; // return to end program
        }

        try { // try block for solving maze without path
            System.out.println("Starting maze at: " + Arrays.toString(maze.getLeftOpening()));
            System.out.println("Starting right hand rule");
            logger.info("**** Computing path");

            explorer.exploreRightHandRule(); // explore maze with right hand rule
            List<String> moves = explorer.getMoves(); 
            Path path = new Path(moves); // initiate path with moves
            
            // print final paths
            System.out.println("Maze solved!"); 
            System.out.println("Final canonical path: " + path.getFormattedPath());
            System.out.println("Final factorized path: " + path.getFactorizedPath());

        } catch(Exception e) { // catch block for exceptions
            logger.error("/!\\ An error has occurred /!\\ Error:{}", e.getMessage());
        }

        logger.info("** End of MazeRunner"); // end of program
    }
}