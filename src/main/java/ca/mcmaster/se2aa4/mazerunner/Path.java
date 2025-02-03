package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> steps; // List to store each step taken by the explorer in the maze

    // Constructor that initializes the steps list
    public Path() {
        steps = new ArrayList<>();
    }

    // Adds a step to the path
    public void addStep(String step) {
        steps.add(step); // Adds a movement to the steps list
    }

    // Returns the canonical path
    public String getCanonicalPath() {
        return String.join(" ", steps); // Joins all steps into a single string with spaces in between
    }

    // Returns a factorized path
    public String getFactorizedPath() {
        if (steps.isEmpty()) {
            return ""; // Returns an empty string if no steps have been taken
        }
        StringBuilder sb = new StringBuilder(); // StringBuilder to construct the factorized path string
        String last = steps.get(0); 
        int count = 1; // Count of consecutive identical steps

        for (int i = 1; i < steps.size(); i++) {
            String current = steps.get(i);
            if (current.equals(last)) {
                count++; // Incrementing count if the current step is the same as the last step
            } else {
                if (count > 1) {
                    sb.append(count).append(last); // Append the count and the step if count is greater than 1
                } else {
                    sb.append(last); // Otherwise, just append the step
                }
                sb.append(" "); // Adds space after each group of steps
                last = current; // Update the last step
                count = 1; // Reset the count to 1
            }
        }
        if (count > 1) {
            sb.append(count).append(last); 
        } else {
            sb.append(last);
        }
        return sb.toString().trim(); // Return the built string, and remove excess spaces
    }
}
