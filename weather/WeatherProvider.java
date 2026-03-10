package weather;

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

    public String getCurrentWeather(Coordinates p_coordinates) {
        int sum = p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight();
        return weather[sum % 4];
    }
}
