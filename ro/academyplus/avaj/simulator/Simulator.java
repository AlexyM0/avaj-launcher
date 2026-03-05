package ro.academyplus.avaj.simulator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import ro.academyplus.avaj.tower.WeatherTower;
import ro.academyplus.avaj.vehicles.AircraftFactory;
import ro.academyplus.avaj.vehicles.Flyable;

public class Simulator {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Error: expected one argument (scenario file)");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            String firstLine = reader.readLine();
            if (firstLine == null) {
                reader.close();
                throw new IllegalArgumentException("scenario file is empty");
            }
            if (firstLine.trim().isEmpty()) {
                reader.close();
                throw new IllegalArgumentException("first line must be a positive integer (number of simulations)");
            }

            int nbSimulations = Integer.parseInt(firstLine.trim());
            if (nbSimulations <= 0) {
                reader.close();
                throw new IllegalArgumentException("number of simulations must be positive");
            }

            Logger.open();
            WeatherTower weatherTower = new WeatherTower();
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                if (parts.length != 5)
                    throw new IllegalArgumentException("line " + lineNumber + ": expected format TYPE NAME LONGITUDE LATITUDE HEIGHT");
                int longitude = Integer.parseInt(parts[2]);
                int latitude = Integer.parseInt(parts[3]);
                int height = Integer.parseInt(parts[4]);
                if (longitude < 0 || latitude < 0 || height < 0)
                    throw new IllegalArgumentException("line " + lineNumber + ": coordinates must be positive");
                if (height > 100)
                    throw new IllegalArgumentException("line " + lineNumber + ": height must be between 0 and 100");
                Flyable aircraft = AircraftFactory.newAircraft(parts[0], parts[1], longitude, latitude, height);
                aircraft.registerTower(weatherTower);
            }
            reader.close();

            for (int i = 0; i < nbSimulations; i++) {
                weatherTower.changeWeather();
            }
            Logger.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found: " + args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid number format");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
