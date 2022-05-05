package M15FinalProjectKompellaAvineesh.com.company.FinalProject.CoinAPI;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class CurrentCrypto {

    // Makes URL based on cryptocurrency symbol
    private static String urlMaker(String key, String symbol) {
        return "https://rest.coinapi.io/v1/assets/" + symbol + "?apikey=" + key;
    }

    // Tries to get JSON data based on URL. If something goes wrong, the console will report the error
    private static CryptoResponse[] getJson(String url) {

        WebClient coinClient = WebClient.create(url);

        try {
            Mono<CryptoResponse[]> cryptoResponse = coinClient.
                    get().
                    retrieve().
                    bodyToMono(CryptoResponse[].class);
            // NOTE the CryptoResponse[] - the JSON data that's returned is wrapped around [] for some reason, so we
            // have to convert it to CryptoResponse[]

            return cryptoResponse.share().block();

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

    // Displays the crypto information based on JSON data.
    private static void displayInfo(CryptoResponse[] json) {

        System.out.println();

        System.out.println("CRYPTO DATA:\n-------------");

        System.out.println("Currency Name: " + getName(json));
        System.out.println("Symbol: " + getSymbol(json));
        System.out.println("Current price in USD: " + getPrice(json));
    }

    // Gets the cryptocurrency name based on JSON data
    private static String getName(CryptoResponse[] json) {
        return json[0].name;
    }

    // Gets the cryptocurrency symbol based on JSON data
    private static String getSymbol(CryptoResponse[] json) {
        return json[0].asset_id;
    }

    // Gets the cryptocurrency price based on JSON data, formatted into proper USD format (e.g., $10,000.00).
    private static String getPrice(CryptoResponse[] json) {
        double price = Math.floor(Double.valueOf(json[0].price_usd) * 100.0 + 0.5) / 100.0;
        return "$" + price;
    }

    // How other classes get the info from the private methods above.
    public static void coinOption(String symbol) {
        String coinAPIKey = "4D1397BF-B719-4CCD-9200-5D7B73A9A80B";
        String url = urlMaker(coinAPIKey, symbol);

        CryptoResponse[] cryptoResponse = getJson(url);

        if (cryptoResponse.length > 0) { // Signifies that crypto data actually exists for the inputted symbol
            displayInfo(cryptoResponse);
        } else {
            System.out.println("Error: Unable to find cryptocurrency.");
        }
    }
}
