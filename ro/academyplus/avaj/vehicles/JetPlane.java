package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.weather.Coordinates;
import ro.academyplus.avaj.weather.WeatherProvider;
import ro.academyplus.avaj.simulator.Logger;

public class JetPlane extends Aircraft {
    private WeatherProvider weatherProvider;

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
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
                Logger.write("JetPlane#" + name + "(" + id + "): It's sunny. Time to break the sound barrier!");
                latitude += 10;
                height += 2;
                break;
            case "RAIN":
                Logger.write("JetPlane#" + name + "(" + id + "): It's raining. Better watch out for lightings.");
                latitude += 5;
                break;
            case "FOG":
                Logger.write("JetPlane#" + name + "(" + id + "): Flying blind through the fog!");
                latitude += 1;
                break;
            case "SNOW":
                Logger.write("JetPlane#" + name + "(" + id + "): OMG! Winter is coming!");
                height -= 7;
                break;
        }

        if (height > 100) height = 100;
        if (height < 0) height = 0;

        this.coordinates = new Coordinates(longitude, latitude, height);

        if (this.coordinates.getHeight() == 0) {
            Logger.write("JetPlane#" + name + "(" + id + ") landing.");
            weatherTower.unregister(this);
        }
    }
}
