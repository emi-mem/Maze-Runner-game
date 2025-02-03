package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private int x, y; // Coordinates of the explorer in the maze
    private final Maze maze; // The maze the explorer is navigating
    private Direction currentDirection; // The current direction the explorer is facing

    // initializes the explorer with a starting position and direction
    public Explorer(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.x = startX;
        this.y = startY;
        
        // Start facing east if on the west border, and west if on the east border
        this.currentDirection = startX == 0 ? Direction.EAST : Direction.WEST;
    }

    // Enum to define all possible directions with methods to change directions
    enum Direction {
        NORTH, EAST, SOUTH, WEST;

        // Rotate clockwise
        public Direction turnRight() {
            return Direction.values()[(this.ordinal() + 1) % 4];
        }

        // Rotate anti-clockwise
        public Direction turnLeft() {
            return Direction.values()[(this.ordinal() + 3) % 4];
        }
    }

    // Check if movement in the current direction is possible without hitting walls
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

    // Move the explorer one step in the current direction
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

    // Accessor methods to get the current x and y coordinates
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Returns the current position as a string formatted as "(x, y)"
    public String getCurrentPosition() {
        return "(" + x + ", " + y + ")";
    }

    // To move the explorer one step 
    public boolean moveStep(Path path) {
        Direction originalDirection = currentDirection;

        // Try to turn right and move
        currentDirection = originalDirection.turnRight();
        if (canMove()) {
            move();
            path.addStep("R");
            path.addStep("F");
            return true;
        }

        // If right turn is not possible, try to move straight
        currentDirection = originalDirection;
        if (canMove()) {
            move();
            path.addStep("F");
            return true;
        }

        // If moving straight is not possible, try to turn left and move
        currentDirection = originalDirection.turnLeft();
        if (canMove()) {
            move();
            path.addStep("L");
            path.addStep("F");
            return true;
        }

        // If no other movement is possible, turn around and move
        currentDirection = originalDirection.turnRight().turnRight();
        if (canMove()) {
            move();
            path.addStep("R");
            path.addStep("R");
            path.addStep("F");
            return true;
        }

        // If stuck and no movement is possible, return false.
        return false;
    }
}
