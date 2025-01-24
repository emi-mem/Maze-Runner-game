package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> steps = new ArrayList<>();

    public void addStep(String step) {
        steps.add(step);
    }

    public String getPath() {
        return String.join(", ", steps);
    }
}
