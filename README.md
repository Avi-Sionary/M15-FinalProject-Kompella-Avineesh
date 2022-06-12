# M15-FinalProject-Kompella-Avineesh

## About
Command-line application that interacts with numerous APIs to retrieve information about the world. Built for the Java Fundamentals Bootcamp run by Netflix Career Pathways + 2U.

Uses the following APIs:

### CoinAPI 
Grabs information on current Cryptocurrency prices.

### OpenWeather API 
Grabs the weather conditions in a location.

### Open Notify: Current ISS Location 
Sends the current location of the International Space Station in latitude and longitude.

API Keys are used for CoinAPI and OpenWeather API.

Project created using Spring Initializr, and the Spring Reactive Web dependency.

## Main application

Creates a menu for users to select the information they want to receive, as follows:

### Weather in a City

Allows users to input a city and print the weather in that city.

![ProjectDemo](https://user-images.githubusercontent.com/22825635/173209884-8a138206-c714-4718-9e3c-63e8de909e95.gif)

### Location of the International Space Station (ISS)

Returns the current latitude and longitude of the ISS and uses the Weather API to return the city and country that correspond to those coordinates.

If the coordinates return null for the country, the app prints that the ISS is not currently in a country.

![ProjectDemo2](https://user-images.githubusercontent.com/22825635/173210062-f45e9ec0-f57f-4cee-b43a-a067b2e544b7.gif)

### Weather in the Location of the ISS

Returns the same information as the second option but also returns the weather in that location, even if there is no valid country.

![ProjectDemo3](https://user-images.githubusercontent.com/22825635/173210069-57099f31-025b-49be-bf99-aab9da5a6104.gif)

### Current Cryptocurrency Prices

Allows a user to input the symbol of a cryptocurrency (such as BTC or ETH) and returns the name of the currency, the symbol, and the current price in USD, formatted into proper USD format ($10,000.00).

![ProjectDemo4](https://user-images.githubusercontent.com/22825635/173210073-0e309603-0317-4b1e-aec3-4bf3560bea5a.gif)

### BONUS: Popular News Articles

Allows a user to input a search query representing a topic and returns a list of articles related to that query sorted by popularity. 

![ProjectDemo5](https://user-images.githubusercontent.com/22825635/173210092-e66fd2fa-aa7c-452f-b363-815154863328.gif)

### Exit

Quits the application.

## Classes
There are classes that represent the JSON response from each API.

### Weather API

#### WeatherResponse class 
Represents each key-value pair from the JSON response.

### ISS API

#### SpaceResponse class 
Represents each key-value pair from the JSON response.

### Cryptocurrency API

#### CryptoResponse class 
Represents only the key-value pairs for Name, Id, and Price in USD.

Each class includes exception handling to create a stable application.

## Grading criteria
The following criteria have been met:

### General Setup and Formatting: 25%
- All code must reside in one IntelliJ project called M15-FinalProject-Lastname-Firstname.

- Project is initialized using Spring Initializr.

- Project incorporates exception handling.

- Project runs in the command line.

### Weather API: 20%
Solution must implement the following operations using the Weather API:

- Weather in a city.

- Weather at the ISS coordinates.

- Print city and country based on ISS coordinates.

- Print if ISS is not in a country.

- Response class represents all key-value pairs.

### ISS API: 20%
Solution must implement the following operations using the ISS API:

- Returns the location of the ISS.

- Integrates with Weather API to print weather and location name.

- Response class represents all key-value pairs.

### Crypto API: 25%
Solution must implement the following using the Cryptocurrency API:

- Returns name, id, and price of input cryptocurrency.

- Price of cryptocurrency is formatted correctly.

- Response class represents the required key-value pairs.

### CLI: 20%
Solution must use the command line and implement the following:

- User input is used to control the app.

- Menu options are presented to user.

- User can exit the app via a menu choice.

- Menu and responses are well presented in the command line and are readable.
