package ca.mcmaster.se2aa4.mazerunner.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Path;

public class PathTest {
    private Path path;

    @BeforeEach
    public void setUp() {
        path = new Path();
    }

    @Test
    public void testCanonicalPath() {
        path.addStep("F");
        path.addStep("F");
        path.addStep("R");
        path.addStep("F");
        assertEquals("F F R F", path.getCanonicalPath(), "Canonical path should join steps with spaces");
    }

    @Test
    public void testFactorizedPathSimple() {
        path.addStep("F");
        path.addStep("F");
        path.addStep("F");
        // Expected: "3F" because three consecutive forward moves compress into 3F.
        assertEquals("3F", path.getFactorizedPath(), "Factorized path should compress consecutive F's");
    }
    
    @Test
    public void testFactorizedPathMixed() {
        path.addStep("F");
        path.addStep("F");
        path.addStep("R");
        path.addStep("R");
        path.addStep("F");
        // Expected: "2F 2R F" for two forward moves, two right turns, and one forward move.
        assertEquals("2F 2R F", path.getFactorizedPath(), "Factorized path should compress mixed steps correctly");
    }
    
    @Test
    public void testEmptyPath() {
        assertEquals("", path.getCanonicalPath(), "Empty path should return an empty canonical string");
        assertEquals("", path.getFactorizedPath(), "Empty path should return an empty factorized string");
    }
}
