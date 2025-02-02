package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

// Path class to handle the path inputted by the user
public class Path {
    private static final Logger logger = LogManager.getLogger();
    private List<String> path;
    private List<String> inputtedPath;

    private String stringInputPath;
    private String formattedPath;
    private String formattedInputtedPath;
    private String factorizedPath;
    private String factorizedInputtedPath; // initialize all the variables

    public Path(List<String> path) { // constructor for when only the path is generated
        this.path = path;
        formattedPath = formatMoves(this.path);
        factorizedPath = factorizeMoves(this.path);

        logger.info("Factorized path: {}", factorizedPath);
        logger.info("Canonical path: {}", formattedPath);
    }

    public Path(String inputtedPath) { // constructor for when the path is inputted by the user

        String input = convertFormat(inputtedPath);
        this.stringInputPath = input;

        this.inputtedPath = pathToList(input);

        formattedInputtedPath = formatMoves(this.inputtedPath);
        factorizedInputtedPath = factorizeMoves(this.inputtedPath);

        logger.info("Factorized inputted path: {}", factorizedInputtedPath);
        logger.info("Canonical inputted path: {}", formattedInputtedPath);
    }

    public Path(List<String> path, String inputtedPath) { // constructor for when both the path is generated and inputted

        this.path = path;

        String input = convertFormat(inputtedPath);
        this.stringInputPath = input;
        this.inputtedPath = pathToList(input);


        formattedPath = formatMoves(this.path);
        factorizedPath = factorizeMoves(this.path);
        formattedInputtedPath = formatMoves(this.inputtedPath);
        factorizedInputtedPath = factorizeMoves(this.inputtedPath);

        logger.info("Initialized path: {}", factorizedPath);
        logger.info("Canonical path: {}", formattedPath);

        logger.info("Initialized inputted path: {}", factorizedInputtedPath);
        logger.info("Canonical inputted path: {}", formattedInputtedPath);
    }

    private List<String> pathToList(String path) { // convert the path to a list of strings
        List<String> pathList = new ArrayList<>();

        for (char move : path.toCharArray()) {
            pathList.add(String.valueOf(move));
        }
        return pathList;
    }

    private String formatMoves(List<String> moves) { // format the moves ("FFFR" to "FFF R")

        if (moves.isEmpty()) return ""; // if the list is empty, return an empty string

        StringBuilder formattedMoves = new StringBuilder();
        String lastMove = moves.getFirst();
        int count = 1;

        for (int i = 1; i < moves.size(); i++) {
            if (moves.get(i).equals(lastMove)) { // if the current move is the same as the last move, increment the count
                count++;
            } else { // if the current move is different from the last move, append the last move and count to the string
                formattedMoves.append(lastMove.repeat(count)).append(" "); // append the last move to the string, repeated count times
                lastMove = moves.get(i);
                count = 1;
            }
        }
        formattedMoves.append(lastMove.repeat(count)); // append the last move to the string, repeated count times

        return formattedMoves.toString().trim(); // return the formatted string
    }

    private String factorizeMoves(List<String> path) { // factorize the moves ("FFF R" to "3F 1R")
        if (path.isEmpty()) return ""; // if the list is empty, return an empty string

        StringBuilder factorizedPath = new StringBuilder();
        String lastMove = path.getFirst();
        int count = 1;

        for (int i = 1; i < path.size(); i++) { 
            if (path.get(i).equals(lastMove)) { // if the current move is the same as the last move, increment the count
                count++;
            } else { // if the current move is different from the last move, append the last move and count to the string
                factorizedPath.append(count > 1 ? count : "").append(lastMove).append(" "); // append the last move to the string, repeated count times
                lastMove = path.get(i); 
                count = 1;
            }
        }
        factorizedPath.append(count > 1 ? count : "").append(lastMove); // append the last move to the string, repeated count times

        return factorizedPath.toString().trim(); // return the factorized string
    }

    private String convertFormat(String input) { // convert the inputted path to the format used by the program
        StringBuilder regularPath = new StringBuilder();
        StringBuilder numberBuffer = new StringBuilder(); // initialize the string builders

        String[] parts = input.split("\\s+"); // split the input by spaces

        for (String part : parts) { // iterate through the parts
            for (int i = 0; i < part.length(); i++) { // iterate through the characters in the part

                char currentChar = part.charAt(i); 

                if (Character.isDigit(currentChar)) { // if the character is a digit, append it to the number buffer
                    numberBuffer.append(currentChar);

                } else { // if the character is not a digit
                    if (!numberBuffer.isEmpty()) { // if the number buffer is not empty
                        int repeatCount = Integer.parseInt(numberBuffer.toString()); // parse the number buffer to an integer
                        regularPath.append(String.valueOf(currentChar).repeat(repeatCount)); // append the current character to the regular path, repeated repeatCount times
                        numberBuffer.setLength(0);

                    } else { // if the number buffer is empty
                        regularPath.append(currentChar); // append the current character to the regular path
                    }
                }
            }

            if (!numberBuffer.isEmpty()) { // if the number buffer is not empty
                int repeatCount = Integer.parseInt(numberBuffer.toString()); // parse the number buffer to an integer
                regularPath.append(String.valueOf(part.charAt(part.length() - 1)).repeat(repeatCount)); // append the last character of the part to the regular path, repeated repeatCount times
                numberBuffer.setLength(0); 
            }
        }

        return regularPath.toString(); // return the regular path
    }

    // bunch of getters
    public String getInputtedPath() {
        return stringInputPath;
    }

    public String getFormattedPath() {
        return formattedPath;
    }

    public String getFormattedInputtedPath() {
        return formattedInputtedPath;
    }

    public String getFactorizedPath() {
        return factorizedPath;
    }

    public String getFactorizedInputtedPath() {
        return factorizedInputtedPath;
    }
}