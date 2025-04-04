package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LargeMazeTest {
    private Maze maze;
    private Explorer explorer;

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/large.maz.txt");
        explorer = new Explorer(maze);
    }

    @Test
    void testMazeWestWithInput() {
        String westPath = "F R 8F 2L 2F R 2F R 4F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F L 8F 2L 6F R 2F R 4F 2L 4F L 2F R 2F R 4F R 8F R 2F L 2F R 2F L 2F 2L 2F R 2F R 2F 2L 4F R 2F L 2F R 2F 2L 2F R 4F R 2F R 2F 2L 2F L 2F R 2F L 2F R 8F R 4F R 2F R 2F L 2F 2L 2F R 4F R 2F R 2F L 2F R 2F 2L 2F R 2F 2L 4F R 2F R 4F 2L 4F R 2F R 6F R 2F L 2F 2L 2F R 2F R 4F R 2F 2L 2F R 2F R 4F L 2F R 2F 2L 2F L 2F R 4F R 4F R 2F R 2F 2L 2F R 4F L 2F 2L 2F R 2F R 4F R 4F R 2F L 2F 2L 2F R 2F R 2F L 4F 2L 4F R 2F R 2F 2L 2F R 2F R 6F R 2F R 2F 2L 2F R 2F L 6F 2L 6F R 4F R 2F R 2F L 4F 2L 4F R 2F L 2F R 2F 2L 2F R 6F R 2F R 2F 2L 6F R 4F R 2F R 2F L 6F R 2F 2L 2F L 6F R 2F R 8F R 2F L 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F L 2F R 8F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F 2L 2F R 2F L 2F R 2F R 4F R 2F L 2F 2L 2F R 2F L 2F R 2F R 4F R 2F 2L 4F R 2F R 4F 2L 2F R 2F R 2F 2L 2F R 2F R 4F R 4F 2L 2F R 4F L 2F R 2F R 2F L 2F R 2F 2L 2F R 2F L 2F 2L 2F R 4F R 2F R 2F L 2F R 6F L 4F 2L 4F R 2F R 4F 2L 4F R 2F R 2F 2L 2F R 2F L 2F R 2F R 4F R 2F L 2F R 2F 2L 2F L 2F R 2F R 2F 2L 6F R 2F R 2F 2L 2F R 2F 2L 2F R 2F R 4F R 4F R 2F L 2F R 2F L 2F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 2F R 2F 2L 2F L 2F R 4F R 4F R 2F R 2F 2L 2F R 4F 2L 4F R 2F R 4F L 2F 2L 2F R 4F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 4F R 2F 2L 2F L 4F R 2F R 4F 2L 2F R 2F R 2F L 8F 2L 2F R 2F L 2F 2L 2F R 2F R 6F R 2F R 2F 2L 4F R 2F R 2F 2L 4F R 2F R 6F R 2F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 4F R 2F R 6F 2L 4F R 2F R 6F 2L 2F R 2F R 2F 2L 2F L 2F R 4F R 2F R 2F 2L 2F R 2F R 6F L 4F 2L 2F R 2F 2L 2F R 2F R 6F L 2F R 2F R 4F R 4F L 2F R 2F 2L 2F L 2F R 6F R 2F R 4F 2L 4F L 2F L 2F R 4F L 2F R 2F R 2F 2L 2F R 2F R 4F 2L 2F R 2F R 4F 2L 2F R 2F 2L 2F R 2F R 2F L 2F R 6F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F L 2F R 2F R 4F R 2F 2L 2F R 4F R 6F R 2F R 4F 2L 4F R 2F R 2F 2L 2F R 2F 2L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F L 2F R 2F R 4F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F 2L 2F R 2F R 4F L 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 4F 2L 4F R 2F R 4F 2L 4F L 6F R 2F R 2F 2L 2F L 2F R 2F R 8F R 2F R 4F 2L 2F R 2F R 2F 2L 4F R 2F 2L 2F L 2F R 2F R 4F 2L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F 2L 2F R 2F R 4F L 2F R 2F R 2F L 2F 2L 2F R 2F R 4F R 2F L 4F R 6F R 2F R 4F 2L 4F R 2F R 4F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F L 4F R 2F R 2F 2L 6F R 6F R 2F R 4F 2L 4F L 2F R 4F R 2F R 2F 2L 2F R 2F R 8F 2L 4F R 2F R 4F 2L 2F R 2F R 2F 2L 2F L 2F R 2F R 4F R 6F R 2F 2L 2F R 4F 2L 4F R 2F R 2F 2L 2F R 2F 2L 4F R 2F R 2F 2L 2F R 4F L 6F R 2F R 4F 2L 4F R 2F L 4F R 2F R 6F R 2F 2L 4F 2L 2F R 8F R 2F R 4F 2L 6F R 2F R 2F 2L 4F R 2F R 2F 2L 2F L 2F R 4F R 4F R 2F R 2F 2L 2F R 2F 2L 4F R 2F R 6F R 2F 2L 2F R 2F R 4F 2L 4F R 2F R 6F 2L 6F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F 2L 2F R 2F R 4F 2L 6F R 2F R 4F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F 2L 2F R 2F R 2F L 4F 2L 4F R 2F R 6F 2L 6F R 2F R 8F L 2F R 2F R 2F 2L 2F R 2F 2L 2F R 2F R 2F 2L 2F L 2F R 2F L 2F R 6F R 2F R 4F L 2F R 2F 2L 2F L 2F R 4F R 2F R 2F 2L 2F R 2F R 4F 2L 4F L 4F R 2F R 2F 2L 2F L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F R 6F R 2F R 2F L 4F R 2F L 4F R 2F R 2F 2L 2F R 8F R 2F L 4F 2L 4F R 4F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 6F 2L 4F R 2F R 2F L 2F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 4F R 2F R 6F R 2F 2L 2F R 4F R 2F R 2F 2L 2F R 2F R 2F 2L 4F 2L 2F R 2F R 2F 2L 2F R 2F R 4F 2L 2F R 2F 2L 2F R 12F R 4F R 2F R 2F 2L 2F R 2F R 2F L 4F 2L 4F R 2F R 6F R 2F 2L 2F R 4F 2L 10F R 2F R 8F 2L 2F R 2F R 4F R 2F 2L 2F R 2F R 2F 2L 2F L 2F R 2F R 2F 2L 2F L 2F R 4F L 2F R 6F R 2F R 2F 2L 2F L 2F R 2F R 4F R 6F R 2F 2L 2F R 4F 2L 6F R 2F 2L 2F R 4F L 4F L 2F R 2F R 4F R 2F 2L 4F R 2F R 2F 2L 2F L 2F R 4F R 4F R 2F R 2F 2L 2F R 2F 2L 4F R 2F R 2F 2L 4F R 2F R 4F 2L 4F R 4F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 6F 2L 4F R 2F R 6F 2L 4F R 2F R 6F 2L 4F R 2F R 4F 2L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F R 6F L 2F R 6F R 2F R 2F 2L 6F 2L 4F R 2F R 4F 2L 12F R F";
        Path path = new Path(westPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        assertTrue(mv.getIsValidWest(), "Maze is valid from west path");
    }

    @Test
    void testMazeEastWithInput() {
        String eastPath = "F L 2F R 2F R 4F R 2F 2L 2F R 2F L 2F R 2F L 2F R 2F L 2F R 2F L 2F R 2F R 4F R 2F L 2F R 2F 2L 2F R 2F R 4F 2L 2F R 2F R 4F 2L 4F L 2F R 2F R 2F L 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 4F R 6F 2L 2F R 2F L 2F 2L 2F R 2F R 4F R 2F 2L 4F R 6F R 2F R 4F L 2F 2L 2F R 8F R 2F 2L 2F R 2F R 4F R 4F R 2F 2L 2F L 8F R 4F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 4F L 2F R 2F R 2F 2L 2F L 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F L 2F R 2F R 4F R 2F L 2F R 2F L 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 4F 2L 4F L 2F R 2F R 2F L 2F 2L 2F R 2F R 2F L 2F L 2F 2L 2F R 2F R 4F R 4F R 2F 2L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F R 2F L 4F L 2F R 2F R 2F 2L 2F R 4F R 2F R 2F 2L 4F 2L 2F R 2F R 2F L 2F R 4F R 2F R 2F L 2F 2L 2F R 2F R 2F L 2F 2L 2F R 2F R 6F R 2F R 2F 2L 2F L 2F R 2F R 2F L 15F";
        Path path = new Path(eastPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        assertTrue(mv.getIsValidEast(), "Maze is valid from east path");
    }
}