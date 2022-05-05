package M15FinalProjectKompellaAvineesh.com.company.FinalProject;

import M15FinalProjectKompellaAvineesh.com.company.FinalProject.CoinAPI.CurrentCrypto;
import M15FinalProjectKompellaAvineesh.com.company.FinalProject.ISSAPI.ISSLocation;
import M15FinalProjectKompellaAvineesh.com.company.FinalProject.WeatherAPI.WeatherCity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication

public class FinalProjectApplication {

	public static void main(String[] args) {

		boolean inProgram = true; // Keeps user in the main menu. If false, application quits.
		boolean subProgram = false; // Keeps user within a specific menu option. If false, user goes back to main menu.

		Scanner scanner = new Scanner(System.in);

		while (inProgram) {

			subProgram = true;

			System.out.println("\nPlease enter an option:\n" +
					"A) Weather in a City\n" +
					"B) Location of the International Space Station (ISS)\n" +
					"C) Weather in the Location of the ISS\n" +
					"D) Current Cryptocurrency Prices\n" +
					"E) Exit\n");

			String option = scanner.nextLine().toUpperCase();

			switch (option) {
				case "A": // Weather in a City
					while (subProgram) {

						System.out.println("\nPlease enter a city name. Or, type B to go back, or E to exit altogether: ");
						String city = scanner.nextLine().toUpperCase();

						switch (city) {
							case "B": // User wants to go back to main menu
								subProgram = false;
								break;

							case "E": // User wants to quit application
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default: // User has typed in a city to get its weather
								System.out.println("Please wait...\n");
								String result = WeatherCity.weatherOption("BYCITY", city, 0, 0);
						}
					}
					break;

				case "B": // Location of the International Space Station (ISS)
					while (subProgram) {

						System.out.println("Please wait...\n");
						ISSLocation.issOption("B");

						System.out.println("\nPlease press enter or return to try again. Or, type B to go back, or E to " +
								"exit altogether: ");
						String choice = scanner.nextLine().toUpperCase();

						switch (choice) {
							case "B": // User wants to go back to main menu
								subProgram = false;
								break;

							case "E": // User wants to quit application
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default: // User tries to get location again
								break;
						}
					}
					break;

				case "C": // Weather in the Location of the ISS
					while (subProgram) {

						System.out.println("Please wait...\n");
						ISSLocation.issOption("C");

						System.out.println("\nPlease press enter or return to try again. Or, type B to go back, or E to " +
								"exit altogether: ");
						String choice = scanner.nextLine().toUpperCase();

						switch (choice) {
							case "B": // User wants to go back to main menu
								subProgram = false;
								break;

							case "E": // User wants to quit application
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default: // User wants to get weather based on coordinates again
								break;
						}
					}
					break;

				case "D": // Current Cryptocurrency Prices
					while (subProgram) {

						System.out.println("Please enter a cryptocurrency symbol (such as BTC or ETH): ");
						String symbol = scanner.nextLine();

						System.out.println("Please wait...\n");
						CurrentCrypto.coinOption(symbol);

						System.out.println("\nPlease press enter or return to try again. Or, type B to go back, or E to " +
								"exit altogether: ");

						String choice = scanner.nextLine().toUpperCase();

						switch (choice) {
							case "B": // User wants to go back to main menu
								subProgram = false;
								break;

							case "E": // User wants to quit application
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default: // User wants to get cryptocurrency prices again
								break;
						}
					}
					break;

				case "E": // User wants to quit application
					inProgram = false;
					System.out.println("Goodbye\n");
					break;

				default: // Unrecognized input. Application will report an error before displaying main menu once more
					System.out.println("Error\n");
					break;
			}
		}
	}
}

