package tower;

import java.util.ArrayList;
import java.util.List;
import simulator.Logger;

import vehicles.Flyable;

public class Tower {

    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
        Logger.write("Tower says: " + p_flyable + " registered to weather tower.");
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        Logger.write("Tower says: " + p_flyable + " unregistered from weather tower.");
    }

    protected void conditionChanged() {
        for (Flyable p_flyable : new ArrayList<>(observers)) {
            p_flyable.updateConditions();
        }
    }


}
