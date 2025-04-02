package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExplorerTest {
    private Maze maze;
    private Explorer explorer;
    private Path path;
    // Path to the test maze file (ensure this file exists and is correctly formatted)
    private final String testMazeFilePath = "src/test/resources/straight.maz.txt";

    @BeforeEach
    public void setUp() {
        try {
            maze = new Maze(testMazeFilePath);
            int entryY = maze.getEntryY();
            explorer = new Explorer(maze, 0, entryY);
            path = new Path();
        } catch (IOException e) {
            fail("Setup failed: " + e.getMessage());
        }
    }

    @Test
    public void testInitialPosition() {
        int entryY = maze.getEntryY();
        assertEquals(0, explorer.getX(), "Explorer should start at x = 0");
        assertEquals(entryY, explorer.getY(), "Explorer should start at the entry Y coordinate");
    }

    @Test
    public void testMoveStep() {
        int initialX = explorer.getX();
        int initialY = explorer.getY();
        // Call moveStep passing in the path for recording moves.
        boolean moved = explorer.moveStep();
        assertTrue(moved, "Explorer should be able to move");
        // Expect that the explorer's position changes (for a straight maze, typically moving east).
        assertTrue(explorer.getX() > initialX || explorer.getY() != initialY, "Explorer position should change after moveStep");
    }
    
    @Test
    public void testMoveUntilExit() {
        // Continue moving until the explorer reaches the east border or a move fails.
        int moves = 0;
        while (explorer.getX() < maze.getWidth() - 1 && moves < 50) {
            boolean moved = explorer.moveStep();
            if (!moved) break;
            moves++;
        }
        // For a straight maze, the explorer should reach the east border.
        assertEquals(maze.getWidth() - 1, explorer.getX(), "Explorer should reach the east border");
    }
}

