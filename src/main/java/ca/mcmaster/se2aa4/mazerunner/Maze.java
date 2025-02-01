package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private boolean[][] walls;
    private int width, height;

    public Maze(String filename) throws IOException {
        loadMaze(filename);
    }

    private void loadMaze(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        height = lines.size();
        width = lines.stream().mapToInt(String::length).max().orElseThrow(() -> new IOException("Failed to determine the maze width."));
        walls = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            String currentLine = lines.get(y);
            for (int x = 0; x < currentLine.length(); x++) {
                walls[y][x] = (currentLine.charAt(x) == '#');
            }
            for (int x = currentLine.length(); x < width; x++) {
                walls[y][x] = true; // Pad the rest of the row with walls
            }
        }
    }

    public boolean isWall(int x, int y) {
        return walls[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getEntryY() {
        for (int y = 0; y < height; y++) {
            if (!isWall(0, y)) {
                return y;
            }
        }
        return -1; // Indicates error
    }
}
