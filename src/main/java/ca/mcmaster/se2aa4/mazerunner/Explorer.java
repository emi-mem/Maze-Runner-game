package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

/**
 * The Explorer navigates the maze.
 * This version combines the Observer pattern (to notify move events) 
 * with the Command pattern (to encapsulate movement actions).
 */
public class Explorer {
    private int x, y; // Coordinates of the explorer in the maze
    private final Maze maze; // The maze the explorer is navigating
    private Direction currentDirection; // The current direction the explorer is facing
    // List to store observers that want to be notified of moves.
    private List<ExplorerObserver> observers = new ArrayList<>();

    /**
     * Enum to define all possible directions with methods to change directions.
     */
    public enum Direction {
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

    /**
     * Initializes the explorer with a starting position and direction.
     * @param maze The maze to navigate.
     * @param startX Starting x-coordinate.
     * @param startY Starting y-coordinate.
     */
    public Explorer(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.x = startX;
        this.y = startY;
        // Start facing east if on the west border; otherwise, face west.
        this.currentDirection = startX == 0 ? Direction.EAST : Direction.WEST;
    }

    /**
     * Adds an observer that will be notified of move events.
     * @param observer The observer to add.
     */
    public void addObserver(ExplorerObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered observers of a move event.
     * @param step A string representing the move (e.g., "R", "F").
     */
    private void notifyObservers(String step) {
        for (ExplorerObserver observer : observers) {
            observer.onMove(step);
        }
    }

    /**
     * Checks if movement in the current direction is possible (i.e. no wall in the way).
     * @return true if move is possible; false otherwise.
     */
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

    /**
     * Moves the explorer one step in the current direction.
     * (This method is package-private so command classes can use it.)
     */
    void move() {
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

    /**
     * A built-in moveStep method that uses internal logic (and not commands)
     * to decide on a move and notifies observers accordingly.
     * @return true if a move was made; false if stuck.
     */
    public boolean moveStep() {
        Direction originalDirection = currentDirection;

        // Try to turn right and move.
        currentDirection = originalDirection.turnRight();
        if (canMove()) {
            move();
            notifyObservers("R");
            notifyObservers("F");
            return true;
        }

        // Try to move straight.
        currentDirection = originalDirection;
        if (canMove()) {
            move();
            notifyObservers("F");
            return true;
        }

        // Try to turn left and move.
        currentDirection = originalDirection.turnLeft();
        if (canMove()) {
            move();
            notifyObservers("L");
            notifyObservers("F");
            return true;
        }

        // Try a U-turn (turn right twice) and move.
        currentDirection = originalDirection.turnRight().turnRight();
        if (canMove()) {
            move();
            notifyObservers("R");
            notifyObservers("R");
            notifyObservers("F");
            return true;
        }

        // If no move is possible, return false.
        return false;
    }

    /**
     * Executes a movement command (from the Command pattern) and notifies observers.
     * @param command The movement command to execute.
     */
    public void executeCommand(MovementCommand command) {
        // Execute the command; the command returns a string representing the move.
        String moveCode = command.execute(this);
        // Notify observers about the executed command.
        notifyObservers(moveCode);
    }

    // Getters and setters for position and direction.
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

    /**
     * Returns the current position formatted as "(x, y)".
     * @return The current position string.
     */
    public String getCurrentPosition() {
        return "(" + x + ", " + y + ")";
    }
}
