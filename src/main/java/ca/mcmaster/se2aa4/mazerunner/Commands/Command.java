package ca.mcmaster.se2aa4.mazerunner.Commands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

public abstract class Command {
    protected Explorer explorer;

    public Command(Explorer explorer) {
        this.explorer = explorer;
    }

    public abstract void execute();
}
