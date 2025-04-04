package ca.mcmaster.se2aa4.mazerunner.Commands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class TurnLeftCommand extends Command {
    public TurnLeftCommand(Explorer explorer) {
        super(explorer);
    }
    @Override
    public void execute() {
        explorer.turnLeftCommand();
    }
}