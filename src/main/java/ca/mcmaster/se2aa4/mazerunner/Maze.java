package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    private boolean[][] walls;

    public Maze(String filename) {
        try {
            loadMaze(filename);
        } catch (IOException e) {
            System.err.println("Error loading the maze: " + e.getMessage());
        }
    }

    private void loadMaze(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                if (walls == null) {
                    walls = new boolean[line.length()][line.length()]; // Assuming square maze
                }
                for (int col = 0; col < line.length(); col++) {
                    walls[row][col] = (line.charAt(col) == '#');
                }
                row++;
            }
        }
    }

    public boolean isWall(int x, int y) {
        return walls[x][y];
    }
}
