package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    // A list that will store each step of the path taken by the explorer as a string
    private List<String> steps = new ArrayList<>();

    // Method to add a step to the path
    // Each step is represented as a string, typically indicating a position in the maze
    public void addStep(String step) {
        steps.add(step);  // Add the new step to the end of the list of steps
    }

    // Method to retrieve the entire path taken as a single string
    // for displaying the path or for logging purposes
    public String getPath() {
        // Join all steps in the list with a comma and space for easy readability
        return String.join(", ", steps);
    }
}
