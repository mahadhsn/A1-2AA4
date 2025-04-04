package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Commands.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Explorer { // Explorer class to explore the maze
    private static final Logger logger = LogManager.getLogger();

    private final Maze maze;
    private int[] currentPos;
    private int[] start;
    private int[] end;
    private final boolean[][] visited;
    private List<String> moves;
    private int direction = 0; // directions (0: Right, 1: Down, 2: Left, 3: Up)
    private final List<Command> commandHistory = new ArrayList<>();

    public Explorer(Maze maze) { // constructor
        this.maze = maze;
        this.visited = new boolean[maze.getRows()][maze.getCols()];
        this.currentPos = maze.getLeftOpening();
        this.start = currentPos;
        this.end = maze.getRightOpening();
        this.moves = new ArrayList<>();
        this.direction = 0;
    }

    private void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
    }

    public void exploreWestRightHandRule() { // right hand rule exploration
        initWest(); // initialize explorer for west opening

        if (currentPos == null) {
            logger.error("No valid start point found in maze!");
        }

        logger.info("Starting right-hand rule exploration at position: ({}, {})", currentPos[0], currentPos[1]);

        while(!reachedEnd()) { // while end is not reached
            if (canMoveRight()) { // if can move right, turn right and move forward
                executeCommand(new TurnRightCommand(this));
                executeCommand(new MoveForwardCommand(this));
            }
            else if (canMoveForward()) { // if can move forward, move forward
                executeCommand(new MoveForwardCommand(this));
            }
            else if (canMoveLeft()) { // if can move left, turn left and move forward
                executeCommand(new TurnLeftCommand(this));
                executeCommand(new MoveForwardCommand(this));
            }
            else { // u-turns for dead-ends
                executeCommand(new TurnAroundCommand(this));
                executeCommand(new MoveForwardCommand(this));
            }
        }

        logger.info("Explorer stopped at position: ({}, {})", currentPos[0], currentPos[1]);

        logger.info("Final moves: {}", String.join("", moves)); // Output the final moves
    }

    public void exploreEastRightHandRule() { // right hand rule exploration
        initEast(); // initialize explorer for east opening

        if (currentPos == null) {
            logger.error("No valid start point found in maze!");
        }

        logger.info("Starting right-hand rule exploration at position: ({}, {})", currentPos[0], currentPos[1]);

        while(!reachedEnd()) { // while end is not reached
            if (canMoveRight()) { // if can move right, turn right and move forward
                executeCommand(new TurnRightCommand(this));
                executeCommand(new MoveForwardCommand(this));
            }
            else if (canMoveForward()) { // if can move forward, move forward
                executeCommand(new MoveForwardCommand(this));
            }
            else if (canMoveLeft()) { // if can move left, turn left and move forward
                executeCommand(new TurnLeftCommand(this));
                executeCommand(new MoveForwardCommand(this));
            }
            else { // u-turns for dead-ends
                executeCommand(new TurnAroundCommand(this));
                executeCommand(new MoveForwardCommand(this));
            }
        }

        logger.info("Explorer stopped at position: ({}, {})", currentPos[0], currentPos[1]);

        logger.info("Final moves: {}", String.join("", moves)); // Output the final moves
    }

    public boolean solveWestMazeFromInput(String input) { // solve maze using input string
        initWest(); // initialize explorer for west opening
        int count = 0; // count of moves

        if (input == null || input.isEmpty()) {
            logger.error("Input string is empty or null.");
            return false;
        }

        logger.info("Starting maze exploration from position: ({}, {})", currentPos[0], currentPos[1]);

        for (char move : input.toCharArray()) { // for each move in input string, move accordingly
            count++;
            Command command = switch (move) {
                case 'F' -> new MoveForwardCommand(this);
                case 'R' -> new TurnRightCommand(this);
                case 'L' -> new TurnLeftCommand(this);
                default -> null;
            };

            if (command != null) {
                if (move == 'F' && !canMoveForward()) {
                    logger.warn("Move " + move + " is not allowed at position: ({}, {}) while facing: {}", currentPos[0], currentPos[1], direction);
                    logger.warn("Final moves: {}", String.join("", moves));
                    return false;
                }
                executeCommand(command);
            }
            
            if (reachedEnd() && count == input.length()) { // check if end of the maze was reached while also end of the path
                logger.info("Maze solved from west! Reached the end at position: ({}, {})", currentPos[0], currentPos[1]);
                return true;
            }
            else if (reachedEnd() && count != input.length()) { // if end of maze was reached but path is still continuing, return false
                logger.warn("Reached end but path is still continuing!");
                return false;
            }
        }

        logger.warn("Finished processing input, but did not reach the end. Last position is: ({},{})", currentPos[0], currentPos[1]);
        logger.warn("End should have been: ({},{})", end[0], end[1]);
        logger.warn("Final moves: {}", String.join("", moves));
        return false;
    }

    public boolean solveEastMazeFromInput(String input) { // solve maze using input string
        initEast(); // initialize explorer for west opening
        int count = 0; // count of moves

        if (input == null || input.isEmpty()) {
            logger.error("Input string is empty or null.");
            return false;
        }

        logger.info("Starting maze exploration from position: ({}, {})", currentPos[0], currentPos[1]);

        for (char move : input.toCharArray()) { // for each move in input string, move accordingly
            count++;
            Command command = switch (move) {
                case 'F' -> new MoveForwardCommand(this);
                case 'R' -> new TurnRightCommand(this);
                case 'L' -> new TurnLeftCommand(this);
                default -> null;
            };


            if (command != null) {
                if (move == 'F' && !canMoveForward()) {
                    logger.warn("Move {} is not allowed at position: ({}, {}) while facing: {}", move, currentPos[0], currentPos[1], direction);
                    logger.warn("Final moves: {}", String.join("", moves));
                    return false;
                }
                executeCommand(command);
            }

            if (reachedEnd() && count == input.length()) { // check if end of the maze was reached while also end of the path
                logger.info("Maze solved from east! Reached the end at position: ({}, {})", currentPos[0], currentPos[1]);
                return true;
            }
            else if (reachedEnd() && count != input.length()) { // if end of maze was reached but path is still continuing, return false
                logger.warn("Reached end but path is still continuing!");
                return false;
            }
        }

        logger.warn("Finished processing input, but did not reach the end. Last position is: ({},{})", currentPos[0], currentPos[1]);
        logger.warn("End should have been: ({},{})", end[0], end[1]);
        logger.warn("Final moves: {}", String.join("", moves));
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

    public void moveForwardCommand() { // move forward
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
            moves.add("F");
        }
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

    public void turnRightCommand() {
        direction = (direction + 1) % 4; // Turn 90 degrees right by adding 1 to direction
        moves.add("R");
    }

    public void turnLeftCommand() {
        direction = (direction + 3) % 4;
        moves.add("L");
    }

    public void turnAroundCommand() {
        direction = (direction + 2) % 4;
        moves.add("L");
        moves.add("L");
    }

    private void initWest() {
        start = maze.getLeftOpening();
        end = maze.getRightOpening();
        currentPos = start;
        direction = 0;
        moves = new ArrayList<>();
    }

    private void initEast() {
        start = maze.getRightOpening();
        end = maze.getLeftOpening();
        currentPos = start;
        direction = 2;
        moves = new ArrayList<>();
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
    
    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }
}