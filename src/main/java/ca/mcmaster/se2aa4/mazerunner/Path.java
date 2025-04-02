package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path implements ExplorerObserver {
    private List<String> steps;

    public Path() {
        steps = new ArrayList<>();
    }

    // This method will be called when the Explorer notifies observers.
    @Override
    public void onMove(String step) {
        addStep(step);
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
                sb.append(" ");
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
