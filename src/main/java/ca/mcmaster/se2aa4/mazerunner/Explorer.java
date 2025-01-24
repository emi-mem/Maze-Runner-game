package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private int x, y;
    private Maze maze;

    public Explorer(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.x = startX;
        this.y = startY;
    }

    public void moveIfPossible() {
        // Try moving East, then South, then West, then North
        if (y < maze.getSize() - 1 && !maze.isWall(x, y + 1)) {
            y++; // Move East
        } else if (x < maze.getSize() - 1 && !maze.isWall(x + 1, y)) {
            x++; // Move South
        }
    }

    public String getCurrentPosition() {
        return "(" + x + ", " + y + ")";
    }

    public boolean canMove() {
        // Check if the explorer has more moves
        return x < maze.getSize() - 1 || y < maze.getSize() - 1;
    }
}
