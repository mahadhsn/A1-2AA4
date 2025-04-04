package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SmallMazeTest {
    private Maze maze;
    private Explorer explorer;

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/small.maz.txt");
        explorer = new Explorer(maze);
    }

    @Test
    void testMazeWestWithInput() {
        String westPath = "F R F 2L 2F R 2F R 2F 2L 4F R 2F R 4F 2L 2F R 4F R 2F R 2F 2L 2F L 2F L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F R 2F L F";
        Path path = new Path(westPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        explorer.attach(mv);
        mv.validateWestMazeWithPath();
        assertTrue(mv.getIsValidWest(), "Maze is valid from west path");
    }

    @Test
    void testMazeEastWithInput() {
        String eastPath = "F R 2F L 2F R 2F R 2F 2L 8F 2L 2F R 2F R 2F L 2F 2L 2F R 2F R 4F R 2F L F R F";
        Path path = new Path(eastPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        explorer.attach(mv);
        mv.validateEastMazeWithPath();
        assertTrue(mv.getIsValidEast(), "Maze is valid from east path");
    }
}