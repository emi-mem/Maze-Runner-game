package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private boolean[][] walls;

    public Maze(String filename) {
        loadMaze(filename);
    }

    private void loadMaze(String filename) {
        walls = new boolean[10][10];  // A 10x10 maze
    }

    public boolean isWall(int x, int y) {
        return walls[x][y];
    }
}
