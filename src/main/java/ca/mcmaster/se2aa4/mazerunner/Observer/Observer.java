package ca.mcmaster.se2aa4.mazerunner.Observer;

public abstract class Observer {
    public Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
    }

    public abstract void update();
}