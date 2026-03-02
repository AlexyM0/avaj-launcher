package ro.academyplus.avaj.weather;

public class WeatherProvider {
    private static WeatherProvider instance = null;

    private WeatherProvider() {
    }

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }
        return instance;
    }

    private String[] weather = { "SUN", "RAIN", "FOG", "SNOW" };

    public String getCurrentWeather(Coordinates coordinates) {
        int sum = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        return weather[sum % 4];
    }
}
