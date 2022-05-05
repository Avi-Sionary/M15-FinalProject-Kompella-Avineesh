package M15FinalProjectKompellaAvineesh.com.company.FinalProject.ISSAPI;

import M15FinalProjectKompellaAvineesh.com.company.FinalProject.WeatherAPI.WeatherCity;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class ISSLocation {

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

    private static double getLat(SpaceResponse json) {
        return json.iss_position.get("latitude");
    }

    private static double getLon(SpaceResponse json) {
        return json.iss_position.get("longitude");
    }

    private static String displayInfo(SpaceResponse json, String option) {

        double lat = getLat(json);
        double lon = getLon(json);

        System.out.println("Current latitude of the International Space Station: " + lat);
        System.out.println("Current longitude of the International Space Station: " + lon);

        WeatherCity wc = buildWeatherCityObj();

        String result = wc.weatherOption("BYCOORDS", "", lat, lon);

        if (option.equals("C") && result != null) {
            result = getCityAndWeather(wc, result);
        }

        return result;
    }

    private static String getCityAndWeather(WeatherCity wc, String cityName) {
        return wc.weatherOption("BYCITY", cityName, 0, 0);
    }

    private static WeatherCity buildWeatherCityObj() {
        return new WeatherCity();
    }

    public static String issOption(String option) {
        SpaceResponse spaceResponse = getJson();
        return displayInfo(spaceResponse, option);
    }

}
