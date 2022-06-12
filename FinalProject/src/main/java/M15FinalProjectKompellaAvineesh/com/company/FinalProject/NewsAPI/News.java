package M15FinalProjectKompellaAvineesh.com.company.FinalProject.NewsAPI;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

public class News {
    // Makes URL based on search query
    private static String urlMaker(String key, String query) {
        return "https://newsapi.org/v2/everything?q=" + query +
                "&sortBy=popularity&apiKey=" + key;
    }

    // Tries to get JSON data based on URL. If something goes wrong, the console will report the error
    private static NewsResponse getJson(String url) {

        WebClient newsClient = WebClient.create(url);

        try {
            Mono<NewsResponse> newsResponse = newsClient.
                    get().
                    retrieve().
                    bodyToMono(NewsResponse.class);

            return newsResponse.share().block();

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

    // Displays the news information based on JSON data.
    private static void displayInfo(NewsResponse json) {

        article[] articles = getArticles(json);

        System.out.println();
        System.out.println("NEWS DATA:\n-------------");
        System.out.println("Top Articles: \n");

        for (int i = 0; i < articles.length; i++) {

            article a = articles[i];

            // Format: From [source]: "[title]"
            System.out.println("From " + a.source.get("name") + ": \"" + a.title + "\"");

            // If the author is not null and the listed author is not the same as the source
            if (a.author != null && !a.source.get("name").equalsIgnoreCase(a.author.toUpperCase())) {
                System.out.println(a.author);
            }

            System.out.println("Published at " + a.publishedAt);

            System.out.println("Read more at: " + a.url + "\n");
            //System.out.println(a.description + "\n");
        }
    }

    // Gets the articles based on JSON data.
    private static article[] getArticles(NewsResponse json) {
        return json.articles;
    }

    // How other classes get the info from the private methods above.
    public static void newsOption(String query) {

        String newsAPIKey = "166f69dc3646401eba589d9558853f37";
        String url = urlMaker(newsAPIKey, query);

        NewsResponse newsResponse = getJson(url);

        displayInfo(newsResponse);
    }
}
