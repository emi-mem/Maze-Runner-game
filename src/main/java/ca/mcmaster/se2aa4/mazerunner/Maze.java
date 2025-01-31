package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private boolean[][] walls;
    private int width, height; // Variables for storing the maze's dimensions
    private int entryX, exitX; // Coordinates for entry and exit points

    public Maze(String filename) {
        try {
            loadMaze(filename);
        } catch (IOException e) {
            System.err.println("Error loading the maze: " + e.getMessage());
        }
    }

    private void loadMaze(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> lines = new ArrayList<>();
        String line; 

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        height = lines.size();
        width = lines.get(0).length();
        walls = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                walls[i][j] = (lines.get(i).charAt(j) == '#');
            }
        }
        findEntryAndExit();
    }

    private void findEntryAndExit() {
        // Find the first and last open space in the first and last columns for entry and exit
        for (int i = 0; i < height; i++) {
            if (!walls[i][0]) { // Check for an open space for the entry point
                entryX = i;
                break;
            }
        }
        for (int i = 0; i < height; i++) {
            if (!walls[i][width - 1]) { // Check for an open space for the exit point
                exitX = i;
                break;
            }
        }
    }

    public boolean isWall(int x, int y) {
        return walls[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getEntryX() {
        return entryX;
    }

    public int getExitX() {
        return exitX;
    }
}
