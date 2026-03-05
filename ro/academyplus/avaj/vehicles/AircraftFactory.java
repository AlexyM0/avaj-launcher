package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.weather.Coordinates;

public class AircraftFactory {
    
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        switch (type) {
            case "Balloon":
                return new Balloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
            default:
                throw new IllegalArgumentException("Invalid aircraft");
        }
    }
}
