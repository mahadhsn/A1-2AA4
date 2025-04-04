package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze { // Maze class to load the maze from file
    private char[][] grid;
    private int rows;
    private int cols;
    private int[] leftOpening;
    private int[] rightOpening;

    private static final Logger logger = LogManager.getLogger();

    public Maze(String filePath) { // constructor to load the maze from file
        try {
            loadMaze(filePath);
            getOpenings();
        } catch (IOException e) {
            logger.error("Failed to load the maze from file: {}. Error: {}", filePath, e.getMessage());
        }
    }

    private void loadMaze(String filePath) throws IOException { // load the maze from file

        logger.info("Loading Maze");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath)); // read the file
            String line;
            rows = 0;
            logger.info("**** Reading the maze from file: {}", filePath);

            // first pass: get the grid dimensions
            while ((line = reader.readLine()) != null) {
                cols = line.length();
                rows++;
            }
        } catch (IOException e) { // catch any exceptions
            logger.error("Error determining the grid dimensions from file: {}. Error: {}", filePath, e.getMessage());
            throw e; // re-throw to indicate failure
        }
        reader.close(); 

        // second pass: fill the grid and log each line
        try {
            grid = new char[rows][cols];
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) { 
                grid[row] = line.toCharArray(); // fill the grid
                row++;

                // log the maze using same algorithm from main class
                StringBuilder output = new StringBuilder(); 
                for (int idx = 0; idx < line.length(); idx++) { 
                    if (line.charAt(idx) == '#') {
                        output.append("WALL ");
                    } else if (line.charAt(idx) != '#') {
                        output.append("PASS ");
                    }
                }
                logger.trace(output.toString());
            }
        } catch (IOException e) {
            logger.error("Error populating the maze grid from file: {}. Error: {}", filePath, e.getMessage());
            throw e; // re-throw to indicate failure
        }
        reader.close();
    }

    public void getOpenings() { // get the openings in the maze
        // assumes that the openings will always be either on the left or right only
        // and only one on each
        for (int row = 0; row < rows; row++) { 
            if (grid[row][0] == ' ') { // assign the left opening
                this.leftOpening = new int[]{0, row}; 
            }
            if (grid[row][cols - 1] != '#') { // assign the right opening
                this.rightOpening = new int[]{(cols - 1), row};
            }
        }
        if (this.leftOpening == null) {
            logger.error("Error reading start point");
        }
        if (this.rightOpening == null) {
            logger.error("Error reading end point");
        }
        logger.info("Maze start ({},{}) and end ({},{}) points read successfully!", leftOpening[0],leftOpening[1],rightOpening[0],rightOpening[1]);
    }

    // getters
    public char getGridAt(int x, int y) {
        return grid[y][x];
    }

    public int[] getLeftOpening() {
        return leftOpening;
    }

    public int[] getRightOpening() {
        return rightOpening;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}