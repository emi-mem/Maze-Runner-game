package ca.mcmaster.se2aa4.mazerunner;

/**
 * Command to move the explorer forward.
 */
public class MoveForwardCommand implements MovementCommand {
    @Override
    public String execute(Explorer explorer) {
        // Move the explorer forward.
        explorer.move();
        return "F";
    }
}
