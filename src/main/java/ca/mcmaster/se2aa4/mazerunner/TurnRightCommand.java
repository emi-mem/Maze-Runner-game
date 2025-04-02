package ca.mcmaster.se2aa4.mazerunner;

/**
 * Command to turn the explorer to the right.
 */
public class TurnRightCommand implements MovementCommand {
    @Override
    public String execute(Explorer explorer) {
        // Update the explorer's current direction by turning right.
        explorer.setCurrentDirection(explorer.getCurrentDirection().turnRight());
        return "R";
    }
}
