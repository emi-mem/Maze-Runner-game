package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    // 2D array to represent the maze's structure; true for walls, false for open paths.
    private boolean[][] walls;

    // Constructor that initializes the Maze object by loading the maze structure from a file.
    public Maze(String filename) {
        try {
            loadMaze(filename); // Attempt to load the maze from the specified file.
        } catch (IOException e) {
            System.err.println("Error loading the maze: " + e.getMessage()); // Print an error if the file cannot be read.
        }
    }

    // Method to load the maze data from a file.
    private void loadMaze(String filename) throws IOException {
        // Use a BufferedReader to read text from a character-input stream, providing for the efficient reading of characters, arrays, and lines.
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<String> lines = new ArrayList<>(); // Store each line of the file in a List for processing.

            // Read the file line by line until the end of the file.
            while ((line = reader.readLine()) != null) {
                lines.add(line); // Add each line to the list.
            }

            // Initialize the walls array based on the number of lines and the length of the first line (assuming the maze is rectangular).
            walls = new boolean[lines.size()][lines.get(0).length()];
            
            // Convert each character in the lines into a boolean value in the walls array.
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    walls[i][j] = (lines.get(i).charAt(j) == '#'); // Set true for walls ('#').
                }
            }
        }
    }

    // Check if the specified position in the maze is a wall.
    public boolean isWall(int x, int y) {
        return walls[x][y]; // Return true if there's a wall at the position (x, y).
    }

    // Return the size of the maze (assuming it is square for simplicity).
    public int getSize() {
        return walls.length; // Return the number of rows in the maze.
    }
}
