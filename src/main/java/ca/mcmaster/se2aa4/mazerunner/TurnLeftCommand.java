package ca.mcmaster.se2aa4.mazerunner;


public class TurnLeftCommand implements MovementCommand {
    @Override
    public String execute(Explorer explorer) {
        // Update the explorer's current direction by turning left.
        explorer.setCurrentDirection(explorer.getCurrentDirection().turnLeft());
        return "L";
    }
}
