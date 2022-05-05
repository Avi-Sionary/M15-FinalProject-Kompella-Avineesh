package M15FinalProjectKompellaAvineesh.com.company.FinalProject.ISSAPI;

import M15FinalProjectKompellaAvineesh.com.company.FinalProject.WeatherAPI.WeatherCity;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class ISSLocation {

    // Tries to get JSON data based on URL. If something goes wrong, the console will report the error
    private static SpaceResponse getJson() {

        WebClient spaceClient = WebClient.create("http://api.open-notify.org/iss-now.json");

        try {
            Mono<SpaceResponse> spaceResponse = spaceClient
                    .get()
                    .retrieve()
                    .bodyToMono(SpaceResponse.class);

            return spaceResponse.share().block();

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

    // Gets the latitude of the ISS based on JSON data
    private static double getLat(SpaceResponse json) {
        return json.iss_position.get("latitude");
    }

    // Gets the longitude of the ISS based on JSON data
    private static double getLon(SpaceResponse json) {
        return json.iss_position.get("longitude");
    }

    // Displays ISS information based on JSON data. If the user is coming here from option C in
    // FinalProjectApplication.java, then this method will return the weather data for the city that the ISS
    // latitude/longitude coordinates correspond to, if any.
    private static String displayInfo(SpaceResponse json, String option) {

        double lat = getLat(json);
        double lon = getLon(json);

        System.out.println("Current latitude of the International Space Station: " + lat);
        System.out.println("Current longitude of the International Space Station: " + lon);

        WeatherCity wc = buildWeatherCityObj();

        String result = wc.weatherOption("BYCOORDS", "", lat, lon);

        if (option.equals("C") && result != null) { // If user wants weather data based on coordinates
            result = getCityAndWeather(wc, result);
        }

        return result;
    }

    // Calls on WeatherCity class object public function to get weather info for cityName.
    private static String getCityAndWeather(WeatherCity wc, String cityName) {
        return wc.weatherOption("BYCITY", cityName, 0, 0);
    }

    // Creates a new WeatherCity class object
    private static WeatherCity buildWeatherCityObj() {
        return new WeatherCity();
    }

    // // How other classes get the info from the private methods above.
    public static String issOption(String option) {
        SpaceResponse spaceResponse = getJson();
        return displayInfo(spaceResponse, option);
    }

}
