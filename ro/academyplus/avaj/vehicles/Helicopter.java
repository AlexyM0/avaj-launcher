package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.weather.Coordinates;
import ro.academyplus.avaj.weather.WeatherProvider;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherProvider weatherProvider;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
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
                longitude += 10;
                height += 2;
                break;
            case "RAIN":
                longitude += 5;
                break;
            case "FOG":
                longitude += 1;
                break;
            case "SNOW":
                height -= 12;
                break;
        }

        if (height > 100) height = 100;
        if (height < 0) height = 0;

        this.coordinates = new Coordinates(longitude, latitude, height);
    }
}
