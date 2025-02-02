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
            System.out.println("Could not parse command-line arguments.");
            return;
        }

        String inputFilePath = inputHandler.getInputFilePath(); // initiate inputFilePath
        String inputPath = inputHandler.getMazePath(); // initiate inputPath

        if (inputFilePath == null) { // exception case for inputFilePath
            logger.error("Failed to read File Path.");
            System.out.println("Could not read file path. Make sure to provide a valid file path with the -i flag.");
            return;
        }
        
        Maze maze = new Maze(inputFilePath); // initiate maze
        Explorer explorer = new Explorer(maze); // initiate explorer

        if (maze.getLeftOpening() == null) {
            logger.error("No valid starting point found in the maze.");
            System.out.println("Error loading maze with file path: " + inputFilePath);
            return;
        }

        if (inputPath.equals("empty")) { // exception case for inputPath
            logger.info("No path provided.");
        }

        else if (inputPath == null) {
            logger.error("Failed to read Maze Path.");
            System.out.println("Could not read maze path.");
            return;
        }
        
        else { // if path is provided

            Path path = new Path(inputPath); // initiate path

            if (path.getIsValidFormattedInputtedPath() == false) {
                logger.error("Invalid inputted path.");
                System.out.println("Invalid inputted path: " + inputPath);
                return;
            }

            System.out.println("Inputted canonical path: " + path.getFormattedInputtedPath());
            System.out.println("Inputted factorized path: " + path.getFactorizedInputtedPath());
            
            System.out.println("");

            System.out.println("Solving maze with path from west"); 
            System.out.println("Starting maze at: " + Arrays.toString(explorer.getStart())); // print starting position

            MazeValidator mazeValidator = new MazeValidator(maze, explorer, path); // initiate mazeValidator
            
            if (mazeValidator.getIsValidWest() == false) {
                logger.error("Maze could not be solved from west side with the provided path.");
                System.out.println("Maze could not be solved from west side with the provided path. Ended at position: " + Arrays.toString(explorer.getCurrentPosition()));
            }
            else {
                System.out.println("Maze solved from west with inputted path!");
            }

            System.out.println("");

            System.out.println("Solving maze with path from east"); 
            System.out.println("Starting maze at: " + Arrays.toString(explorer.getStart())); // print starting position

            if (mazeValidator.getIsValidEast() == false) {
                logger.error("Maze could not be solved from east side with the provided path.");
                System.out.println("Maze could not be solved from east side with the provided path. Ended at position: " + Arrays.toString(explorer.getCurrentPosition()));
            }
            else {
                System.out.println("Maze solved from east with inputted path!");
            }

            return; // return to end program
        }

        try { // try block for solving maze without path
            
            System.out.println("Starting right hand rule algorithm");
            System.out.println("");
            logger.info("**** Computing path");

            explorer.exploreWestRightHandRule(); // explore maze with right hand rule from west
            List<String> movesWest = explorer.getMoves(); 
            Path pathWest = new Path(movesWest); // initiate path with moves
            
            // print final paths
            System.out.println("Starting maze at: " + Arrays.toString(explorer.getStart()));
            System.out.println("Maze solved from west!"); 
            System.out.println("Final canonical west path: " + pathWest.getFormattedPath());
            System.out.println("Final factorized west path: " + pathWest.getFactorizedPath());
            System.out.println("");

            explorer.exploreEastRightHandRule(); // explore maze with right hand rule from east
            List<String> movesEast = explorer.getMoves(); 
            Path pathEast = new Path(movesEast); // initiate path with moves
            
            // print final paths
            System.out.println("Starting maze at: " + Arrays.toString(explorer.getStart()));
            System.out.println("Maze solved from east!"); 
            System.out.println("Final canonical east path: " + pathEast.getFormattedPath());
            System.out.println("Final factorized east path: " + pathEast.getFactorizedPath());

        } catch(Exception e) { // catch block for exceptions
            logger.error("/!\\ An error has occurred /!\\ Error:{}", e.getMessage());
        }

        logger.info("** End of MazeRunner"); // end of program
    }
}