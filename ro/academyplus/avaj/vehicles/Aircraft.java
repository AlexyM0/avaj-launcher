package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.weather.Coordinates;

public abstract class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    public String toString() {
        return this.getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
}
