package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputHandler {
    private static final Logger logger = LogManager.getLogger(InputHandler.class);

    private CommandLine cmd;
    private final Options opts;

    public InputHandler() {
        opts = new Options();
        opts.addOption("i", "input", true, "Path to the maze input file");
        opts.addOption("p", "Path", true, "Solve maze based on inputted path");
    }

    public boolean parseArgs(String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            cmd = parser.parse(opts, args);
            return true;
        } catch (ParseException e) {
            logger.error("Error parsing arguments: {}", e.getMessage());
            return false;
        }
    }

    public String getInputFilePath() {
        if (cmd != null && cmd.hasOption("i")) {
            return cmd.getOptionValue("i");
        }
        else {
            logger.error("/!\\ Missing required -i flag for input file /!\\");
            return null;
        }
    }

    // implement this properly later
    public String getMazePath() {
        if (cmd != null && cmd.hasOption("p")) {
            return cmd.getOptionValue("p");
        }
        else {
            logger.error("/!\\ Missing required -p flag for input file /!\\");
            return null;
        }
    }
}