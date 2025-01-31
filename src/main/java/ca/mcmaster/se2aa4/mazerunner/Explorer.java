package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private int x, y;
    private final Maze maze;
    private Direction currentDirection;

    public Explorer(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.x = startX;
        this.y = startY;
        this.currentDirection = Direction.EAST; // Assuming starting facing East
    }

    enum Direction {
        NORTH, EAST, SOUTH, WEST;

        public Direction turnRight() {
            return Direction.values()[(this.ordinal() + 1) % 4];
        }

        public Direction turnLeft() {
            return Direction.values()[(this.ordinal() + 3) % 4];
        }
    }

    public void moveIfPossible() {
        Direction originalDirection = currentDirection;
        currentDirection = currentDirection.turnRight(); // Try to turn right first
        if (!canMove()) {
            currentDirection = originalDirection; // Reset to original direction and try to move straight
            if (!canMove()) {
                currentDirection = currentDirection.turnLeft(); // Turn left and try
                if (!canMove()) {
                    currentDirection = currentDirection.turnLeft(); // Turn back (180 degrees)
                }
            }
        }
        move();
    }

    private void move() {
        switch (currentDirection) {
            case NORTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y++;
                break;
            case WEST:
                x--;
                break;
        }
    }

    public boolean canMove() {
        switch (currentDirection) {
            case NORTH:
                return y > 0 && !maze.isWall(x, y - 1);
            case EAST:
                return x < maze.getWidth() - 1 && !maze.isWall(x + 1, y);
            case SOUTH:
                return y < maze.getHeight() - 1 && !maze.isWall(x, y + 1);
            case WEST:
                return x > 0 && !maze.isWall(x - 1, y);
        }
        return false;
    }

    public String getCurrentPosition() {
        return "(" + x + ", " + y + ")";
    }
}
