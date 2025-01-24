package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

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
        logger.info("**** Reading the maze from file " + args[0]);
        
        Maze maze = new Maze(inputFile);
        Explorer explorer = new Explorer(maze, 0, 0); // Starting at position (0,0)
        Path path = new Path();

        while (explorer.canMove()) {
            explorer.moveEast();
            path.addStep(explorer.getCurrentPosition());
        }
        
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
