package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class MazeTest {
    private final String testMazeFilePath = "src/test/resources/straight.maz.txt";

    @Test
    public void testMazeDimensions() {
        try {
            Maze maze = new Maze(testMazeFilePath);
            assertEquals(5, maze.getWidth(), "Maze width should be 5");
            assertEquals(5, maze.getHeight(), "Maze height should be 5");
        } catch (IOException e) {
            fail("Maze initialization failed: " + e.getMessage());
        }
    }

    @Test
    public void testIsWall() {
    try {
        Maze maze = new Maze("src/test/resources/straight.maz.txt");
        int entryY = maze.getEntryY();
        // The entry cell (on the west border) should not be a wall.
        assertFalse(maze.isWall(0, entryY), "Entry cell should not be a wall");
        // In a fully open maze, any cell within bounds should not be a wall.
        assertFalse(maze.isWall(maze.getWidth() - 1, 0), "Top-right cell should be open in a straight maze");
    } catch (IOException e) {
        fail("Maze initialization failed: " + e.getMessage());
    }
}


    @Test
    public void testGetEntryY() {
        try {
            Maze maze = new Maze(testMazeFilePath);
            int entryY = maze.getEntryY();
            // The entry Y should be within the valid row indices.
            assertTrue(entryY >= 0 && entryY < maze.getHeight(), "Entry Y should be a valid row index");
        } catch (IOException e) {
            fail("Maze initialization failed: " + e.getMessage());
        }
    }
}
