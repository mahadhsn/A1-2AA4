package ca.mcmaster.se2aa4.mazerunner.Commands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class TurnAroundCommand extends Command {
    public TurnAroundCommand(Explorer explorer) {
        super(explorer);
    }
    @Override
    public void execute() {
        explorer.turnAroundCommand();
    }
}