package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Explorer { // Explorer class to explore the maze
    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private int[] currentPos;
    private int[] start;
    private int[] end;
    private boolean[][] visited;
    private List<String> moves;
    private int direction = 0; // directions (0: Right, 1: Down, 2: Left, 3: Up)

    public Explorer(Maze maze) { // constructor
        this.maze = maze;
        this.visited = new boolean[maze.getRows()][maze.getCols()];
        this.currentPos = maze.getLeftOpening();
        this.start = currentPos;
        this.end = maze.getRightOpening();
        this.moves = new ArrayList<>();
        this.direction = 0;
    }

    public void exploreMazeBasic() {
        // very basic moveForward function where it only moves forward until it encounters a wall then terminates
        // implement better algorithms for final release
        // move forward good for mvp

        if (currentPos == null) {
            logger.error("No valid starting point found in the maze.");
            return;
        }

        logger.info("Starting exploration at position: ({}, {})", currentPos[0], currentPos[1]);

        while (moveForward()) {
            logger.trace("Moved forward to position: ({}, {})", currentPos[0], currentPos[1]);
            if (currentPos == end)  {
                break;
            }
        }
        logger.info("Explorer stopped at position: ({}, {})", currentPos[0], currentPos[1]);

        logger.info("Final moves: {}", String.join("", moves)); // Output the final moves
    }

    public void exploreRightHandRule() { // right hand rule exploration
        if (currentPos == null) {
            logger.error("No valid start point found in maze!");
        }

        logger.info("Starting right-hand rule exploration at position: ({}, {})", currentPos[0], currentPos[1]);

        while(!reachedEnd()) { // while end is not reached
            if (canMoveRight()) { // if can move right, turn right and move forward
                turnRight();
                moveForward();
            } else if (canMoveForward()) { // if can move forward, move forward
                moveForward();
            } else if (canMoveLeft()) { // if can move left, turn left and move forward
                turnLeft();
                moveForward();
            } else { // u-turns for dead-ends
                turnAround();
                moveForward();
            }
        }

        logger.info("Explorer stopped at position: ({}, {})", currentPos[0], currentPos[1]);

        logger.info("Final moves: {}", String.join("", moves)); // Output the final moves
    }

    public boolean solveMazeFromInput(String input) { // solve maze using input string
        int count = 0; // count of moves

        if (input == null || input.isEmpty()) {
            logger.error("Input string is empty or null.");
            return false;
        }

        logger.info("Starting maze exploration from position: ({}, {})", currentPos[0], currentPos[1]);

        for (char move : input.toCharArray()) { // for each move in input string, move accordingly
            count++;
            if (move == 'F') { 
                if (!canMoveForward()) {
                    logger.warn("Hit a wall during move or out of bounds.");
                    break;
                }
                moveForward();
            } else if (move == 'R') {
                turnRight();
            } else if (move == 'L') {
                turnLeft();
            } else {
                logger.warn("Invalid move character encountered: {}", move);
                return false;
            }

            
            if (reachedEnd() && count == input.length()) { // check if end of the maze was reached while also end of the path
                logger.info("Maze solved! Reached the end at position: ({}, {})", currentPos[0], currentPos[1]);
                return true;
            }
            else if (reachedEnd() && count != input.length()) { // if end of maze was reached but path is still continuing, return false
                logger.warn("Reached end but path is still continuing!");
                return false;
            }
        }

        logger.warn("Finished processing input, but did not reach the end. End position is: ({},{})", currentPos[0], currentPos[1]);
        return false;
    }

    private boolean reachedEnd() { // check if end of maze is reached
        return currentPos[0] == end[0] && currentPos[1] == end[1];
    }

    private boolean isValidMove(int x, int y) { // check if move is valid
        boolean withinBounds = x >= 0 && x < maze.getCols() && y >= 0 && y < maze.getRows(); // check if within bounds

        boolean isNotWall = maze.getGridAt(x, y) != '#'; // check if not a wall

        return withinBounds && isNotWall; // return if both conditions are met
    }

    private boolean moveForward() { // move forward
        int newX = currentPos[0];
        int newY = currentPos[1];
    
        if (direction == 0) {
            newX++; // Move right
        } else if (direction == 1) {
            newY++; // Move down
        } else if (direction == 2) {
            newX--; // Move left
        } else if (direction == 3) {
            newY--; // Move up
        }
    
        if (isValidMove(newX, newY)) { // check if moving forward is possible
            currentPos = new int[]{newX, newY};
            moves.add("F"); // add move to list
            return true;
        }
    
        logger.warn("Hit a wall at position: ({}, {})", newX, newY);
        return false;
    }

    private boolean canMoveForward() { // check if moving forward is possible
        int newX = currentPos[0];
        int newY = currentPos[1];
    
        if (direction == 0) {
            newX++; // Move right
        } else if (direction == 1) {
            newY++; // Move down
        } else if (direction == 2) {
            newX--; // Move left
        } else if (direction == 3) {
            newY--; // Move up
        }
    
        return isValidMove(newX, newY); // check if moving forward is possible or not
    }


    private boolean canMoveRight() { // check if moving right is possible
        direction = (direction + 1) % 4; // methods were not used as to not change the direction of the explorer
        boolean canMove = canMoveForward();
        direction = (direction + 3) % 4;
        return canMove;
    }

    private boolean canMoveLeft() { // check if moving left is possible
        direction = (direction + 3) % 4;
        boolean canMove = canMoveForward();
        direction = (direction + 1) % 4;
        return canMove;
    }

    // very cool ways of turning
    private void turnRight() {
        direction = (direction + 1) % 4; // Turn 90 degrees right by adding 1 to direction
        moves.add("R");
    }

    private void turnLeft() {
        direction = (direction + 3) % 4; // Turn 90 degrees left by adding 3 to direction
        moves.add("L");
    }

    private void turnAround() {
        direction = (direction + 2) % 4; // Turn 180 degrees by adding 2 to direction
        moves.add("L");
        moves.add("L");
    }

    // getters
    public int[] getCurrentPosition() {
        return currentPos;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public List<String> getMoves() {
        return moves;
    }
}