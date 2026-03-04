package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.weather.Coordinates;
import ro.academyplus.avaj.weather.WeatherProvider;
import ro.academyplus.avaj.simulator.Logger;
import ro.academyplus.avaj.tower.WeatherTower;

public class Balloon extends Aircraft implements Flyable {
    private WeatherProvider weatherProvider;
    private WeatherTower weatherTower;

    public Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.weatherProvider = WeatherProvider.getInstance();
    }

    public void registerTower(WeatherTower tower) {
        this.weatherTower = tower;
        tower.register(this);
    }

    @Override
    public void updateConditions() {
        String weather = weatherProvider.getCurrentWeather(this.coordinates);
        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();

        switch (weather) {
            case "SUN":
                Logger.write("Balloon#" + name + "(" + id + "): Let's enjoy the good weather and take some pics.");
                longitude += 2;
                height += 4;
                break;
            case "RAIN":
                Logger.write("Balloon#" + name + "(" + id + "): Damn you rain! You messed up my balloon.");
                height -= 5;
                break;
            case "FOG":
                Logger.write("Balloon#" + name + "(" + id + "): I can't see anything in this fog!");
                height -= 3;
                break;
            case "SNOW":
                Logger.write("Balloon#" + name + "(" + id + "): It's snowing. We're gonna crash.");
                height -= 15;
                break;
        }

        if (height > 100) height = 100;
        if (height < 0) height = 0;

        this.coordinates = new Coordinates(longitude, latitude, height);

        if (this.coordinates.getHeight() == 0) {
            Logger.write("Balloon#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
        }
    }
}
