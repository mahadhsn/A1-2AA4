package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class MediumMazeTest {
    private Maze maze;
    private Explorer explorer;
    private final Logger logger = LogManager.getLogger();
    private final String westPath = "F R 6F 2L 8F R 2F R 2F 2L 2F R 2F R 4F R 2F L 4F L 2F 2L 2F R 4F R 2F L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F R 4F R 2F 2L 2F L 2F R 2F 2L 2F R 2F R 2F 2L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 4F R 2F R 2F 2L 2F R 10F R 2F R 8F 2L 8F L 2F R 4F R 2F R 2F 2L 2F R 2F R 14F 2L 12F R 2F R 6F 2L 4F R 2F R 6F R 2F L 6F 2L 6F R 2F R 8F 2L 12F R 2F R 10F 2L 6F R 2F R 4F 2L 4F L 2F R 4F L 2F R 2F L 2F R 2F L 2F R 2F L 4F R 2F R 2F 2L 4F R 2F R 6F R 2F 2L 2F R 2F R 4F 2L 2F R 2F R 4F 2L 4F R 2F R 2F 2L 2F R 2F R 4F R 2F L 2F 2L 2F R 2F R 6F L 2F R 8F 2L 8F R 2F R 10F R 4F R 2F 2L 2F R 2F 2L 2F R 2F R 2F L 4F R 2F 2L 4F 2L 2F R 4F R 2F R 2F 2L 4F R 2F R 6F 2L 6F R 4F R 2F R 2F L 2F 2L 2F R 4F R 2F R 2F 2L 2F R 2F R 4F 2L 4F L 4F R 2F R 4F 2L 2F R 2F 2L 2F R 2F R 2F 2L 6F R 2F R 8F R 6F R 2F 2L 2F L 2F R F";

    @BeforeEach
    void setUp() {
        maze = new Maze("./examples/medium.maz.txt");
        explorer = new Explorer(maze);
    }

    @Test
    void testMazeWestWithInput() {
        Path path = new Path(westPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        explorer.attach(mv);
        mv.validateWestMazeWithPath();
        assertTrue(mv.getIsValidWest(), "Maze is valid from west path");
    }

    @Test
    void testMazeEastWithInput() {
        String eastPath = "F R 4F L 10F R 2F R 4F 2L 4F R 2F R 4F 2L 4F R 6F R 2F R 4F 2L 4F R 2F R 4F 2L 4F R 4F R 2F R 2F 2L 2F R 2F R 2F L 4F 2L 4F R 2F R 4F L 2F 2L 2F R 4F R 2F R 2F 2L 2F L 2F R 4F R 2F R 2F 2L 4F 2L 2F R 14F R 6F R 2F R 4F 2L 2F R 2F R 2F 2L 2F R 2F R 2F 2L 2F L 4F R 2F L 2F R 4F R 2F R 2F L 2F R 2F 2L 2F R 2F R 2F 2L 2F R 2F R 6F 2L 2F R 2F R 2F L 2F 2L 2F R 2F R 2F 2L 4F R 2F R 6F R 2F 2L 4F L 2F 2L 2F R 2F R 6F R 6F R 2F R 4F 2L 4F R 2F R 2F 2L 2F R 2F R 2F 2L 2F L 8F 2L 2F R 4F R 6F R 2F R 4F 2L 6F R 2F R 4F 2L 4F L 2F R 2F R 2F L 2F 2L 2F R 2F R 4F R 2F L 8F L 4F 2L 2F R 2F R 4F R 2F L 4F 2L 4F R 2F R 6F R 2F 2L 2F R 4F R 2F R 2F 2L 4F 2L 2F R 2F R 4F R 2F L 2F R F";
        Path path = new Path(eastPath);
        MazeValidator mv = new MazeValidator(maze, explorer, path);
        explorer.attach(mv);
        mv.validateEastMazeWithPath();
        assertTrue(mv.getIsValidEast(), "Maze is valid from east path");
    }

    @Test
    void testMazeWest() {
        explorer.exploreWestRightHandRule();
        Path path = new Path(explorer.getMoves());
        assertEquals(westPath, path.getFactorizedPath());
    }
}