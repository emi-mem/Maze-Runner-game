package ca.mcmaster.se2aa4.mazerunner;

public class Explorer {
    private int x, y;
    private final Maze maze;
    private Direction currentDirection;

    public Explorer(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.x = startX;
        this.y = startY;
        this.currentDirection = startX == 0 ? Direction.EAST : Direction.WEST;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCurrentPosition() {
        return "(" + x + ", " + y + ")";
    }

    public boolean moveStep(Path path) {
        Direction originalDirection = currentDirection;
        currentDirection = originalDirection.turnRight();
        if (canMove()) {
            move();
            path.addStep("R");
            path.addStep("F");
            return true;
        }
        currentDirection = originalDirection;
        if (canMove()) {
            move();
            path.addStep("F");
            return true;
        }
        currentDirection = originalDirection.turnLeft();
        if (canMove()) {
            move();
            path.addStep("L");
            path.addStep("F");
            return true;
        }
        currentDirection = originalDirection.turnRight().turnRight();
        if (canMove()) {
            move();
            path.addStep("R");
            path.addStep("R");
            path.addStep("F");
            return true;
        }
        return false;
    }
}
