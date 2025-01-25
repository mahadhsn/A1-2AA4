package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Explorer {
    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private int[] currentPos;
    private int[] start;
    private int[] end;
    private boolean[][] visited;
    private List<String> moves;
    private int direction = 0; // direction, 0: Right, 1: Down, 2: Left, 3: Up

    public Explorer(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.getRows()][maze.getCols()];
        this.currentPos = maze.getLeftOpening();
        this.start = currentPos;
        this.end = maze.getRightOpening();
        this.moves = new ArrayList<>();
        this.direction = 0;
    }

    public void exploreMaze() {
        // very basic moveForward function where it only moves forward until it encounters a wall then terminates
        // implement better algorithms for final release
        // move forward good for mvp

        if (currentPos == null) {
            logger.error("No valid starting point found in the maze.");
            return;
        }

        logger.info("Starting exploration at position: ({}, {})", currentPos[0], currentPos[1]);

        while (moveForward()) {
            moves.add("F");
            logger.trace("Moved forward to position: ({}, {})", currentPos[0], currentPos[1]);
            if (currentPos == end)  {
                break;
            }
        }
        logger.info("Explorer stopped at position: ({}, {})", currentPos[0], currentPos[1]);

        logger.info("Final moves: {}", String.join("", moves)); // Output the final moves
    }

    private boolean isValidMove(int x, int y) {
        boolean withinBounds = x >= 0 && x < maze.getCols() && y >= 0 && y < maze.getRows();

        boolean isNotWall = maze.getGridAt(x, y) != '#';

        return withinBounds && isNotWall;
    }

    private boolean moveForward() {
        int newX = currentPos[0];
        int newY = currentPos[1];

        if (currentPos[0] != end[0] || currentPos[1] != end[1]) {
            switch (direction) {
                case 0:
                    newX++;
                    break; // Move right
                case 1:
                    newY++;
                    break; // Move down
                case 2:
                    newX--;
                    break; // Move left
                case 3:
                    newY--;
                    break; // Move up
            }
        } else {
            logger.trace("Reached end!");
            return false;
        }

        if (isValidMove(newX, newY)) {
            currentPos = new int[]{newX, newY};
            return true;
        }

        logger.warn("Hit a wall at position: ({}, {})", newX, newY);
        return false;
    }

    // very cool ways of turning
    private void turnRight() {
        direction = (direction + 1) % 4;
        logger.trace("Turning Right");
        moveForward();
    }
    private void turnLeft() {
        direction = (direction + 3) % 4;
        logger.trace("Turning Left");
        moveForward();
    }

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