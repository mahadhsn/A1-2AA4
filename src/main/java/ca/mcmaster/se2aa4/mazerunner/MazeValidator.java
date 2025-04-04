package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Observer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeValidator extends Observer { // Class to validate the maze with the provided path

    private static final Logger logger = LogManager.getLogger();
    private final Explorer explorer;
    private final Path path;
    private boolean isValidWest;
    private boolean isValidEast;

    public MazeValidator(Maze maze, Explorer explorer, Path path) {
        super(explorer); // Constructor to initialize the maze, explorer, and path
        this.explorer = explorer;
        this.path = path;
    }

    public void validateEastMazeWithPath() { // Method to validate the maze with the provided path
        String inputPath = path.getInputtedPath(); 

        logger.info("Starting maze validation from east with path: {}", inputPath);
        explorer.solveEastMazeFromInput(inputPath);
    }

    public void validateWestMazeWithPath() { // Method to validate the maze with the provided path
        String inputPath = path.getInputtedPath();

        logger.info("Starting maze validation from west with path: {}", inputPath);
        explorer.solveWestMazeFromInput(inputPath);
    }

    public boolean getIsValidWest() { // Method to return the validity of the maze
        return isValidWest;
    }

    public boolean getIsValidEast() { // Method to return the validity of the maze
        return isValidEast;
    }

    @Override
    public void update() {
        isValidWest = explorer.isValidWest();
        isValidEast = explorer.isValidEast();
    }
}