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
        // Log the start of the application
        logger.info("** Starting Maze Runner");

        // Setting up command line options to parse
        Options options = new Options();
        options.addOption("i", true, "Input maze file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            // Parse the command line arguments
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            // Log error if command line parsing fails
            logger.error("Error parsing command line: " + e.getMessage());
            return;
        }

        if (!cmd.hasOption("i")) {
            // Log error if the input file option is missing
            logger.error("Please provide the input file using -i option");
            return;
        }

        // Retrieve the input file path from the command line arguments
        String inputFile = cmd.getOptionValue("i");
        logger.info("**** Reading the maze from file " + args[0]);
        
       // Create a Maze object from the specified file
       Maze maze = new Maze(inputFile);

       // Create an Explorer to navigate the maze starting at position (0,0)
       Explorer explorer = new Explorer(maze, 0, 0);

       // Create a Path object to track the path taken
       Path path = new Path();

        // Navigate through the maze until no more moves are possible
        while (explorer.canMove()) {
            explorer.moveIfPossible();
            // Record each step in the path
            path.addStep(explorer.getCurrentPosition());
        }
        

       // Log the final path computed
       logger.info("**** Computing path");
       logger.info("PATH NOT COMPUTED");  // Placeholder text; needs actual path computation
       logger.info("** End of MazeRunner");
   }
}
