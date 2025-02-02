package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputHandler { // InputHandler class to handle command line arguments
    private static final Logger logger = LogManager.getLogger(InputHandler.class);

    private CommandLine cmd;
    private final Options opts;

    public InputHandler() { // constructor to initialize Options object
        opts = new Options();
        opts.addOption("i", "input", true, "Path to the maze input file");
        opts.addOption("p", "path", true, "Solve maze based on inputted path");
    }

    public boolean parseArgs(String[] args) { // parseArgs method to parse command line arguments
        CommandLineParser parser = new DefaultParser();
        try {
            cmd = parser.parse(opts, args); // parse command line arguments
            return true;
        } catch (ParseException e) {
            logger.error("Error parsing arguments: {}", e.getMessage());
            return false;
        }
    }

    public String getInputFilePath() { // getInputFilePath method to get input file path if -i flag is present
        if (cmd != null && cmd.hasOption("i")) {
            return cmd.getOptionValue("i");
        }
        else {
            logger.error("/!\\ Missing required -i flag for input file /!\\");
            return null;
        }
    }

    public String getMazePath() { // getMazePath method to get maze path if -p flag is present
        if (cmd != null && cmd.hasOption("p")) {
            return cmd.getOptionValue("p");
        }
        else {
            logger.info("Missing required -p flag for inputted maze path");
            return null;
        }
    }
}