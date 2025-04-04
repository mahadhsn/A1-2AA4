package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StraightMazeTest {
    private Maze maze;
    private Explorer explorer;

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/straight.maz.txt");
        explorer = new Explorer(maze);
    }

    @Test
    void testMazeWestWithInput() {
        String westPath = "4F";
        Path path = new Path(westPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        assertTrue(mv.getIsValidWest(), "Maze is valid from west path");
    }

    @Test
    void testMazeEastWithInput() {
        String eastPath = "4F";
        Path path = new Path(eastPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        assertTrue(mv.getIsValidEast(), "Maze is valid from east path");
    }
}