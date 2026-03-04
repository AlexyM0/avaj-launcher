package ro.academyplus.avaj.tower;

import ro.academyplus.avaj.weather.WeatherProvider;
import ro.academyplus.avaj.weather.Coordinates;

public class WeatherTower extends Tower {
    
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(coordinates);
    }
    
    public void changeWeather() {
        conditionsChanged();
    }
}