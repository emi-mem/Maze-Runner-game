package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private int x, y;
    private Maze maze;

    public Explorer(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.x = startX;
        this.y = startY;
    }

    public void moveEast() {
        if (!maze.isWall(x + 1, y)) {
            x++;
        }
    }

    public String getCurrentPosition() {
        return "(" + x + ", " + y + ")";
    }

    public boolean canMove() {
        // Dummy condition to stop moving, implement your logic
        return x < 9;
    }
}
