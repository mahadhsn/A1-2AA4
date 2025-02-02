package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeValidator { // Class to validate the maze with the provided path

    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Explorer explorer;
    private Path path;
    private boolean isValidWest;
    private boolean isValidEast;

    public MazeValidator(Maze maze, Explorer explorer, Path path) { // Constructor to initialize the maze, explorer, and path
        this.maze = maze;
        this.explorer = explorer;
        this.path = path;
        validateMazeWithPath();
    }

    private void validateMazeWithPath() { // Method to validate the maze with the provided path
        String inputPath = path.getInputtedPath(); 

        logger.info("Starting maze validation with path: {}", inputPath);

        // Use the path to attempt solving the maze
        isValidWest = explorer.solveWestMazeFromInput(inputPath); // Solve the maze from west side with the provided path
        isValidEast = explorer.solveEastMazeFromInput(inputPath); // Solve the maze from east side with the provided path

    }

    public boolean getIsValidWest() { // Method to return the validity of the maze
        return isValidWest;
    }

    public boolean getIsValidEast() { // Method to return the validity of the maze
        return isValidEast;
    }
}