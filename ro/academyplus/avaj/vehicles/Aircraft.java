package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.weather.Coordinates;

public abstract class Aircraft {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = ++idCounter;
        this.name = name;
        this.coordinates = coordinates;
    }
}