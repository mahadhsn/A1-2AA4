package ca.mcmaster.se2aa4.mazerunner.Commands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

public class MoveForwardCommand extends Command {
    public MoveForwardCommand(Explorer explorer) {
        super(explorer);
    }
    @Override
    public void execute() {
        explorer.moveForwardCommand();
    }
}