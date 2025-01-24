package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            walls = new boolean[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    walls[i][j] = (lines.get(i).charAt(j) == '#');
                }
            }
        }
    }

    public boolean isWall(int x, int y) {
        return walls[x][y];
    }

    public int getSize() {
        return walls.length; // Assuming the maze is square
    }
}

