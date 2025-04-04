package ca.mcmaster.se2aa4.mazerunner.Observer;

import java.util.ArrayList;
import java.util.List;

// subject to be observed
public abstract class Subject {

    private final List<Observer> observers = new ArrayList<>(); // list of all observers

    public void attach(Observer observer) { // attach observers to the subject
        observers.add(observer);
    }

    // detach method not created since there was no use for it as of now

    public void notifyAllObservers() { // notify all observers at once
        for (Observer observer : observers) {
            observer.update();
        }
    }
}