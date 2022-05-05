package M15FinalProjectKompellaAvineesh.com.company.FinalProject.WeatherAPI;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class WeatherCity {

    // Makes URL based on city query
    private static String urlMaker(String key, String cityName) {
        return "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=imperial&appid=" + key;
    }

    // Makes URL based on coordinates query
    private static String urlMaker(String key, double latitude, double longitude) {
        return "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + key;
    }

    // Tries to get JSON data based on URL. If something goes wrong, the console will report the error
    private static WeatherResponse getJson(String url) {

        WebClient weatherClient = WebClient.create(url);

        try {
            Mono<WeatherResponse> weatherResponse = weatherClient
                    .get()
                    .retrieve()
                    //.bodyToMono(WeatherResponse.class);
                    .bodyToMono(WeatherResponse.class);

            return weatherResponse.share().block();

        } catch (WebClientResponseException we) {
            int statusCode = we.getRawStatusCode();

            if (statusCode >= 400 && statusCode < 500) {
                System.out.println("Client error");
            } else if (statusCode >= 500 && statusCode < 600) {
                System.out.println("Server error");
            }

            System.out.println("Message: " + we.getMessage());

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return null;
    }

    // Displays the weather information based on JSON data. Returns the city name in case the user has come here from
    // option C in FinalProjectApplication.java
    private static String displayInfo(WeatherResponse json) {

        System.out.println();
        System.out.println("WEATHER DATA FOR " + json.name.toUpperCase() + ", " + json.sys.get("country")
                + " (ID " + json.id + "):\n-------------");

        System.out.println("City Longitude: " + json.coord.get("lon"));
        System.out.println("City Latitude: " + json.coord.get("lat"));
        System.out.println();

        System.out.println("Weather: " + json.weather[0].main);
        System.out.println();

        System.out.println("Base: " + json.base);
        System.out.println();

        System.out.println("Temperature: " + json.main.get("temp") + " K");
        System.out.println("Feels like: " + json.main.get("feels_like") + " K");
        System.out.println("Minimum temperature is: " + json.main.get("temp_min") + " K");
        System.out.println("Maximum temperature is: " + json.main.get("temp_max") + " K");
        System.out.println("Atmospheric Pressure: " + json.main.get("pressure") + " hPa");
        System.out.println("Humidity: " + json.main.get("humidity") + "%");
        System.out.println();

        System.out.println("Visibility: " + json.visibility + " km");
        System.out.println();

        System.out.println("Wind speed: " + json.wind.get("speed") + " meter/sec");
        System.out.println("Wind direction: " + json.wind.get("deg") + " degrees");
        System.out.println();

        System.out.println("Cloudiness: " + json.clouds.get("all") + "%");
        System.out.println();

        System.out.println("Timezone shift: " + json.timezone + " seconds from UTC");

        return getCity(json);
    }

    // Gets the city name based on JSON data
    private static String getCity(WeatherResponse json) {
        return json.name;
    }

    // Gets the country name based on JSON data
    private static String getCountry(WeatherResponse json) {
        return json.sys.get("country");
    }

    // Gets city name from the latitude/longitude coordinates, if user is coming here from option C in
    // FinalProjectApplication.java. If the International Space Station is not currently in a country, the application
    // will tell the user and return null.
    private static String getCityFromCoords(WeatherResponse json) {
        if (getCountry(json) == null) {
            System.out.println("The ISS is currently not in any country.");
        } else {
            System.out.println("These coordinates correspond to " + getCity(json) + ", " + getCountry(json));
            return getCity(json);
        }
        return null;
    }

    // How other classes get the info from the private methods above.
    public static String weatherOption(String option, String cityName, double latitude, double longitude) {

        String weatherAPIKey= "312444bc10841f16418912a210aaf02b";

        if (option.equals("BYCITY")) { // If user is searching by city (option A)
            String url = urlMaker(weatherAPIKey, cityName);
            WeatherResponse weatherResponse = getJson(url);
            return displayInfo(weatherResponse);
        }

        else if (option.equals("BYCOORDS")) { // If user is searching by coordinates (option C)
            String url = urlMaker(weatherAPIKey, latitude, longitude);
            WeatherResponse weatherResponse = getJson(url);
            return getCityFromCoords(weatherResponse);
        }

        return null;
    }
}
