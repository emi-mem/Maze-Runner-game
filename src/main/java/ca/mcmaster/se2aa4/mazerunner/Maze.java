package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private boolean[][] walls; 
    private int width, height; // To store the dimensions of the maze

    // Constructor that loads a maze from a file
    public Maze(String filename) throws IOException {
        loadMaze(filename); // Loads the maze from the specified file when an instance is created.
    }

    // Loads the maze structure from a file 
    private void loadMaze(String filename) throws IOException {
        List<String> lines = new ArrayList<>(); // List to hold each line of the file, representing rows of the maze
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line); // Read each line from the file and add to the list
            }
        }

        height = lines.size(); // The number of lines in the file determines the height of the maze
        // Determine the maximum width of the maze based on the longest line in the file
        width = lines.stream().mapToInt(String::length).max().orElseThrow(() -> new IOException("Failed to determine the maze width."));
        
        walls = new boolean[height][width]; 
        for (int y = 0; y < height; y++) {
            String currentLine = lines.get(y);
            for (int x = 0; x < currentLine.length(); x++) {
                walls[y][x] = (currentLine.charAt(x) == '#'); // Set true for walls
            }
            for (int x = currentLine.length(); x < width; x++) {
                walls[y][x] = true; 
            }
        }
    }

    // Checks if a specific coordinate in the maze is a wall
    public boolean isWall(int x, int y) {
        return walls[y][x];
    }

    // Returns the width of the maze
    public int getWidth() {
        return width;
    }

    // Returns the height of the maze
    public int getHeight() {
        return height;
    }

    // Finds the y-coordinate of the entry point on the west border of the maze
    public int getEntryY() {
        for (int y = 0; y < height; y++) {
            if (!isWall(0, y)) { // If the cell on the west border is not a wall, it's the entry point
                return y;
            }
        }
        return -1; // Return -1 if no entry is found, indicating an error
    }
}
