package ca.mcmaster.se2aa4.mazerunner.Observer;

// setup the abstract class for the observer design pattern
public abstract class Observer {
    protected Subject subject; // contains the subject to observe

    public Observer(Subject subject) { // constructor includes the subject to instantiate it
        this.subject = subject;
    }

    public abstract void update(); // abstract update method to be called wherever needed
}