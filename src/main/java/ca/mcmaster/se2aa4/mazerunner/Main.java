package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        // Register Path as an observer of Explorer.
        explorer.addObserver(path);

        while (explorer.getX() < maze.getWidth() - 1) {
            if (!explorer.moveStep()) {
                logger.error("Explorer is stuck. No further moves possible.");
                break;
            }
        }

        logger.info("**** Computing path");
        System.out.println("Canonical Path: " + path.getCanonicalPath());
        System.out.println("Factorized Path: " + path.getFactorizedPath());
        logger.info("** End of MazeRunner");
    }
}
