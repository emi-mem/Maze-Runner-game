package ca.mcmaster.se2aa4.mazerunner;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "Input maze file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

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
        Maze maze = new Maze(inputFile);
        Explorer explorer = new Explorer(maze, 0, 0); // Assuming entry point (0,0) is correct
        Path path = new Path();

        // Navigate the maze using the right-hand rule
        while (explorer.canMove()) {
            explorer.moveIfPossible();
            path.addStep(explorer.getCurrentPosition());
        }

        // Output the path in canonical and factorized form
        logger.info("**** Computing path");
        String canonicalPath = path.getCanonicalPath();
        String factorizedPath = path.getFactorizedPath();
        logger.info("Canonical Path: " + canonicalPath);
        logger.info("Factorized Path: " + factorizedPath);
        logger.info("** End of MazeRunner");
    }
}
