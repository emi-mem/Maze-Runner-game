package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "Input maze file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Error parsing command line: " + e.getMessage());
            return;
        }

        if (!cmd.hasOption("i")) {
            logger.error("Please provide the input file using -i option");
            return;
        }

        String inputFile = cmd.getOptionValue("i");
        Maze maze;
        try {
            maze = new Maze(inputFile);
        } catch (IOException e) {
            logger.error("Error loading maze: " + e.getMessage());
            return;
        }

        int startY = maze.getEntryY();
        if (startY == -1) {
            logger.error("No entry found on the west border.");
            return;
        }

        Explorer explorer = new Explorer(maze, 0, startY);
        Path path = new Path();

        // Ensuring the explorer can exit the loop by reaching the east border or handling being stuck
        while (explorer.getX() < maze.getWidth() - 1) {
            if (!explorer.moveStep(path)) {
                logger.error("Explorer is stuck. No further moves possible.");
                break;
            }
        }

        logger.info("**** Computing path");
        logger.info("Canonical Path: " + path.getCanonicalPath());
        logger.info("Factorized Path: " + path.getFactorizedPath());
        logger.info("** End of MazeRunner");
    }
}