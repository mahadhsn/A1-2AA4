package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TinyMazeTest {
    private Maze maze;
    private Explorer explorer;

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/tiny.maz.txt");
        explorer = new Explorer(maze);
    }

    @Test
    void testMazeWestWithInput() {
        String westPath = "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F";
        Path path = new Path(westPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        explorer.attach(mv);
        mv.validateWestMazeWithPath();
        assertTrue(mv.getIsValidWest(), "Maze is valid from west path");
    }

    @Test
    void testMazeEastWithInput() {
        String eastPath = "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F";
        Path path = new Path(eastPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        explorer.attach(mv);
        mv.validateEastMazeWithPath();
        assertTrue(mv.getIsValidEast(), "Maze is valid from east path");
    }
}