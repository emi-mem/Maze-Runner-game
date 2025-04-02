package ca.mcmaster.se2aa4.mazerunner;

/**
 * Interface for movement commands.
 * Each command implements the execute method that acts on an Explorer.
 * The execute method returns a string representing the move (e.g., "R" or "F").
 */
public interface MovementCommand {
    String execute(Explorer explorer);
}
