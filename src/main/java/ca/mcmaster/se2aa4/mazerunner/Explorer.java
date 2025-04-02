package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Explorer {
    private int x, y;
    private final Maze maze;
    private Direction currentDirection;
    // List to store observers that want to be notified of moves.
    private List<ExplorerObserver> observers = new ArrayList<>();

    public enum Direction {
        NORTH, EAST, SOUTH, WEST;

        public Direction turnRight() {
            return Direction.values()[(this.ordinal() + 1) % 4];
        }

        public Direction turnLeft() {
            return Direction.values()[(this.ordinal() + 3) % 4];
        }
    }

    public Explorer(Maze maze, int startX, int startY) {
        this.maze = maze;
        this.x = startX;
        this.y = startY;
        this.currentDirection = startX == 0 ? Direction.EAST : Direction.WEST;
    }

    // Method to add an observer.
    public void addObserver(ExplorerObserver observer) {
        observers.add(observer);
    }

    // Method to notify all observers of a move.
    private void notifyObservers(String step) {
        for (ExplorerObserver observer : observers) {
            observer.onMove(step);
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

    // Updated moveStep method that uses observers to record moves.
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

        // Try to turn around (U-turn) and move.
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

    // Getters for position.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
