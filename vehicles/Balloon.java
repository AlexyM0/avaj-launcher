package vehicles;

import weather.Coordinates;
import weather.WeatherProvider;
import simulator.Logger;

public class Balloon extends Aircraft {
    private WeatherProvider weatherProvider;

    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
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
