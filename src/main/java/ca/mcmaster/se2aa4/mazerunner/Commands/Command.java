package ca.mcmaster.se2aa4.mazerunner.Commands;

import ca.mcmaster.se2aa4.mazerunner.Explorer;

// setup the abstract class for the command design pattern
// each individual command implements this in its own way
public abstract class Command {
    protected Explorer explorer;

    public Command(Explorer explorer) {
        this.explorer = explorer;
    } // constructor

    public abstract void execute(); // abstract method to execute the commands
}
