package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static final Options options = new Options();
    private static final CommandLineParser cmdParser = new DefaultParser();
    private static final HelpFormatter formatter = new HelpFormatter();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        options.addOption("i", "input", true, "Input to maze runner");

        try {
            CommandLine cmd = cmdParser.parse(options, args);

            if (!cmd.hasOption("i")) {
                logger.error("/!\\ Missing required -i flag for input file /!\\");
                formatter.printHelp("MazeRunner", options);
                return;
            }

            String inputFilePath = cmd.getOptionValue("i");
            logger.info("**** Reading the maze from file: {}", inputFilePath);

            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));

            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder output = new StringBuilder();
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        output.append("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        output.append("PASS ");
                    }
                }
                logger.trace(output.toString());
            }
            reader.close();

        } catch(Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
        }
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}