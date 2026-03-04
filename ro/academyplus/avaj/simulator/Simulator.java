package ro.academyplus.avaj.simulator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import ro.academyplus.avaj.tower.WeatherTower;
import ro.academyplus.avaj.vehicles.AircraftFactory;
import ro.academyplus.avaj.vehicles.Flyable;

public class Simulator {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java Simulator <scenario_file>");
            return;
        }

        try {
            Logger.open();
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));                                                            
            String firstLine = reader.readLine();                                                                                           
            int nbSimulations = Integer.parseInt(firstLine.trim());
            
            WeatherTower weatherTower = new WeatherTower();                                                                                 
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                Flyable aircraft = AircraftFactory.newAircraft(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[4])
                );
                aircraft.registerTower(weatherTower);
            }
            reader.close();
            
            for (int i = 0; i < nbSimulations; i++) {
                weatherTower.changeWeather();
            }
            Logger.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
