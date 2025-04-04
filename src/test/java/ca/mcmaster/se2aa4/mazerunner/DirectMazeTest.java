package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectMazeTest {
    private Maze maze;
    private Explorer explorer;

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/direct.maz.txt");
        explorer = new Explorer(maze);
    }

    @Test
    void testMazeWest() {
        String westPath = "F R 2F L 3F R F L F R F L 2F";
        Path path = new Path(westPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        assertTrue(mv.getIsValidWest(), "Maze is valid from west path");
    }

    @Test
    void testMazeEast() {
        String eastPath = "2F R 2F L 4F R 2F L F";
        Path path = new Path(eastPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        assertTrue(mv.getIsValidEast(), "Maze is valid from east path");
    }
}