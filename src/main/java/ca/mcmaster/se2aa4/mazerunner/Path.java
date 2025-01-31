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

    public String getCanonicalPath() {
        StringBuilder builder = new StringBuilder();
        for (String step: steps) {
            builder.append(step).append(" ");
        }
        return builder.toString().trim();       //trims extra space
    }

    public String getFactorizedPath() {
        if (steps.isEmpty()) return "";

        StringBuilder builder = new StringBuilder();
        String lastStep = steps.get(0);
        int count = 1;

        for (int i= 1; i < steps.size(); i++) {
            String currentStep = steps.get(i);
            if (currentStep.equals(lastStep)) {
                count++;
            } 
            else {
                if (count > 1) {
                    builder.append(count).append(lastStep).append( " ");
                } else {
                    builder.append(lastStep).append(" ");
                }
                lastStep = currentStep;
                count = 1;
            }
        }
        if (count > 1) {
            builder.append(count).append(lastStep);
        }
        else {
            builder.append(lastStep);
        }

        return builder.toString().trim();
    }
 }

