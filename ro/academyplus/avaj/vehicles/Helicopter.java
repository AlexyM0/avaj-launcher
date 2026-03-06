package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.weather.Coordinates;
import ro.academyplus.avaj.weather.WeatherProvider;
import ro.academyplus.avaj.simulator.Logger;

public class Helicopter extends Aircraft {
    private WeatherProvider weatherProvider;

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
        this.weatherProvider = WeatherProvider.getInstance();
    }

    @Override
    public void updateConditions() {
        String weather = weatherProvider.getCurrentWeather(this.coordinates);
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();

        switch (weather) {
            case "SUN":
                Logger.write("Helicopter#" + name + "(" + id + "): This is hot.");
                longitude += 10;
                height += 2;
                break;
            case "RAIN":
                Logger.write("Helicopter#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                longitude += 5;
                break;
            case "FOG":
                Logger.write("Helicopter#" + name + "(" + id + "): Can't see a thing through this fog!");
                longitude += 1;
                break;
            case "SNOW":
                Logger.write("Helicopter#" + name + "(" + id + "): My rotor is going to freeze!");
                height -= 12;
                break;
        }

        if (height > 100) height = 100;
        if (height < 0) height = 0;

        this.coordinates = new Coordinates(longitude, latitude, height);

        if (this.coordinates.getHeight() == 0) {
            Logger.write("Helicopter#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
        }
    }
}
