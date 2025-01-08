## Currency Convert API

This is a simple currency converter api written using Spring Boot. The api lets users to 

- fetch exchange rates between two currencies
- convert the given amount from one currency to the other based on the exchange rate 

#### API Endpoints

##### Get Exchange Rate

**Endpoint:**  /api/rate

**Method:** GET

**Description:** Fetches the exchange rate between two currencies

**Query Params**
- ``from``: The source currency code (eg: USD)
- ``to``: The target currency code (eg: INR)

**Responses**
- ``200 ok``: Returns the exchange rate as a double
- ``400 Bad Request``: Invalid input parameters 

#### Convert Currency

**Endpoint:** /api/convert


**Method:** POST

**Description:** Converts an amount from one currency to another using the fetched exchange rate

**Request Body**

```
{
    "fromCurrency": "USD",
    "toCurrency": "INR",
    "amount": 100
}
```

**Response**
```
{
    "fromCurrency": "USD",
    "toCurrency": "INR",
    "amount": 100,
    "convertedAmount": 8579.679
}
```

**Status Codes**

``200 OK``: Successful conversion

``400 Bad Request``: Invalid input parameters


#### Run locally

##### Prerequisites
- Java Development Kit 
- Maven 
- Open Exchange Rates API app id

##### Steps

1. Clone the repository

```
    git clone git@github.com:aswinr19/currency-converter-api.git
    cd currency-converter-api 
```

2. Set up environment variables

``EXCHANGE_API_URL`` environment variables in your system.
 (or hard code it into src/main/java/com/example/currencyconverter/utils/ExternalApiClient.java line 15)

3. Build the project

```
    mvn clean install
```

4. Run the application

```
    mvn spring-boot:run
```

5. Access the api: The application will start on http://localhost:8080 by default.


##### Run the tests using Maven

```
    mvn test
```
=======
