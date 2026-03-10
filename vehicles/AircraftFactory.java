package vehicles;

import weather.Coordinates;

public class AircraftFactory {
    private static AircraftFactory instance = null;
    private long idCounter = 0;

    private AircraftFactory() {
    }

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        long id = ++idCounter;
        switch (p_type) {
            case "Balloon":
                return new Balloon(id, p_name, p_coordinates);
            case "JetPlane":
                return new JetPlane(id, p_name, p_coordinates);
            case "Helicopter":
                return new Helicopter(id, p_name, p_coordinates);
            default:
                throw new IllegalArgumentException("Invalid aircraft type: " + p_type);
        }
    }
}
