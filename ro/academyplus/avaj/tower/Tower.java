package ro.academyplus.avaj.tower;

import java.util.ArrayList;
import java.util.List;
import ro.academyplus.avaj.simulator.Logger;

import ro.academyplus.avaj.vehicles.Flyable;

public abstract class Tower {
    
    private List<Flyable> observers = new ArrayList<>();
    
    public void register(Flyable flyable) {
        observers.add(flyable);
        Logger.write("Tower says: " + flyable + " registered to weather tower.");
    }
    
    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        Logger.write("Tower says: " + flyable + " unregistered from weather tower.");
    }
    
    protected void conditionsChanged() {
        for (Flyable flyable : new ArrayList<>(observers)) {
            flyable.updateConditions();
        }
    }

    
}