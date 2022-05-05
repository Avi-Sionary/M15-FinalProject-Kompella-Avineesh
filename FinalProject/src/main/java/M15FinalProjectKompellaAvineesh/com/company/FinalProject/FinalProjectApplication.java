package M15FinalProjectKompellaAvineesh.com.company.FinalProject;

import M15FinalProjectKompellaAvineesh.com.company.FinalProject.CoinAPI.CurrentCrypto;
import M15FinalProjectKompellaAvineesh.com.company.FinalProject.ISSAPI.ISSLocation;
import M15FinalProjectKompellaAvineesh.com.company.FinalProject.WeatherAPI.WeatherCity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication

public class FinalProjectApplication {

	public static void main(String[] args) {

		boolean inProgram = true;
		boolean subProgram = false;

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
				case "A":
					while (subProgram) {

						System.out.println("\nPlease enter a city name. Or, type B to go back, or E to exit altogether: ");
						String city = scanner.nextLine().toUpperCase();

						switch (city) {
							case "B":
								subProgram = false;
								break;

							case "E":
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default:
								System.out.println("Please wait...\n");
								String result = WeatherCity.weatherOption("BYCITY", city, 0, 0);
						}
					}
					break;

				case "B":
					while (subProgram) {

						System.out.println("Please wait...\n");
						ISSLocation.issOption("B");

						System.out.println("\nPlease press enter or return to try again. Or, type B to go back, or E to " +
								"exit altogether: ");
						String choice = scanner.nextLine().toUpperCase();

						switch (choice) {
							case "B":
								subProgram = false;
								break;

							case "E":
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default:
								break;
						}
					}
					break;

				case "C":
					while (subProgram) {

						System.out.println("Please wait...\n");
						ISSLocation.issOption("C");

						System.out.println("\nPlease press enter or return to try again. Or, type B to go back, or E to " +
								"exit altogether: ");
						String choice = scanner.nextLine().toUpperCase();

						switch (choice) {
							case "B":
								subProgram = false;
								break;

							case "E":
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default:
								break;
						}
					}
					break;

				case "D":
					while (subProgram) {

						System.out.println("Please enter a cryptocurrency symbol (such as BTC or ETH): ");
						String symbol = scanner.nextLine();

						System.out.println("Please wait...\n");
						CurrentCrypto.coinOption(symbol);

						System.out.println("\nPlease press enter or return to try again. Or, type B to go back, or E to " +
								"exit altogether: ");

						String choice = scanner.nextLine().toUpperCase();

						switch (choice) {
							case "B":
								subProgram = false;
								break;

							case "E":
								subProgram = false;
								inProgram = false;
								System.out.println("Goodbye\n");
								break;

							default:
								break;
						}
					}
					break;

				case "E":
					inProgram = false;
					System.out.println("Goodbye\n");
					break;

				default:
					System.out.println("Error\n");
					break;
			}
		}
	}
}

