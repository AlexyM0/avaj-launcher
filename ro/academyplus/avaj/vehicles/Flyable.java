package ro.academyplus.avaj.vehicles;

import ro.academyplus.avaj.tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower tower);

}