package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> steps;

    public Path() {
        steps = new ArrayList<>();
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public String getCanonicalPath() {
        return String.join(" ", steps);
    }

    public String getFactorizedPath() {
        if (steps.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String last = steps.get(0);
        int count = 1;
        for (int i = 1; i < steps.size(); i++) {
            String current = steps.get(i);
            if (current.equals(last)) {
                count++;
            } else {
                if (count > 1) {
                    sb.append(count).append(last);
                } else {
                    sb.append(last);
                }
                last = current;
                count = 1;
            }
        }
        if (count > 1) {
            sb.append(count).append(last);
        } else {
            sb.append(last);
        }
        return sb.toString().trim();
    }
}
