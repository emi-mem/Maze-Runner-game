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
            
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }
}
