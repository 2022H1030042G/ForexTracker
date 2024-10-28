# Forex Tracker

A Spring Boot application for scraping, storing, and retrieving forex conversion rates. The application scrapes conversion rates daily, stores them in an H2 database, and provides a RESTful API to retrieve average and closing rates for specified currency pairs.

## Features

- **Data Scraping**: Scrapes forex rates daily from a forex website at 6:00 AM.
- **Data Storage**: Stores conversion rates in an H2 database with historical data tracking.
- **API Endpoints**:
  - **Get Average Rate**: Retrieves average conversion rate for a currency pair over a specified date range.
  - **Get Closing Rate**: Retrieves the closing rate for a currency pair on a specific date.
  - **Add Currency Pair**: Allows adding a new currency pair to the tracking list.

## Technology Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Jsoup** (for HTML parsing)
- **H2 Database** (in-memory database for easy setup and testing)
- **Scheduler**: Spring’s `@Scheduled` for automated daily scraping

## Setup and Installation

### Prerequisites

- Java 17 or higher
- Maven

### Steps to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/2022H1030042G/ForexTracker.git
   cd forextracker

2. Open the Project in IntelliJ IDEA: Launch IntelliJ IDEA. Select "Open" and navigate to the cloned repository folder. Open the project.

3. Import Maven Dependencies: IntelliJ should automatically detect the pom.xml file and prompt you to import the Maven project. If not, right-click on the pom.xml file and select "Add as Maven Project". All the dependencies will be downloaded.

4. Run the Application: Locate the main application class (e.g., ForextrackerApplication.java)

# API Endpoints
   
1. Get Average Conversion Rate   
- Endpoint: GET /api/forex/average
- Parameters:
- currencyPair: (String) Currency pair, e.g., USD/INR
- startDate: (String) Start date in YYYY-MM-DD format
- endDate: (String) End date in YYYY-MM-DD format
- Response: JSON with the average conversion rate

Example:
```
http://localhost:8080/api/forex/average?currencyPair=USD/INR&startDate=2024-10-27&endDate=2024-10-27
```

2. Get Closing Conversion Rate
- Endpoint: GET /api/forex/closing
- Parameters:
- currencyPair: (String) Currency pair, e.g., USD/INR
- date: (String) Date in YYYY-MM-DD format
- Response: JSON with the closing conversion rate
  
Example:
```
http://localhost:8080/api/forex/closing?currencyPair=USD/INR&date=2024-10-27
```

3. Add New Currency Pair for Tracking
- Endpoint: POST /api/forex/addCurrencyPair
- Parameters:
- currencyPair: (String) New currency pair to track, e.g., EUR/USD
- Response: Confirmation message in JSON format
- Can use postman to test this API
  - Go to the "Params" tab.
  - Add a key-value pair: currencyPair as the key and the value as the currency pair you want to add (e.g., EUR/USD).
  - Send Request: Click "Send" to submit the POST request.
