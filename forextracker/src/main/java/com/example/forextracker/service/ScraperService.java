package com.example.forextracker.service;

import com.example.forextracker.model.ForexRate;
import com.example.forextracker.model.TrackedCurrencyPair;
import com.example.forextracker.repository.ForexRateRepository;
import com.example.forextracker.repository.TrackedCurrencyPairRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScraperService {

    @Autowired
    private ForexRateRepository forexRateRepository;

    @Autowired
    private TrackedCurrencyPairRepository trackedCurrencyPairRepository;

    @Scheduled(cron = "0 0 6 * * ?") // to run the scheduled task at 6:00 AM every day
//    @Scheduled(cron = "0 */5 * * * ?") // to run the scheduled task at every 5 minutes
    public void scrapeDailyRates() {
        // Placeholder for the scraping logic
        List<TrackedCurrencyPair> trackedPairs = trackedCurrencyPairRepository.findAll();
        for (TrackedCurrencyPair pair : trackedPairs) {
            try {
                // Build the URL for the specific currency pair, e.g., "https://www.x-rates.com/calculator/?from=USD&to=INR&amount=1"
                String[] currencies = pair.getCurrencyPair().split("/");
                String fromCurrency = currencies[0];
                String toCurrency = currencies[1];
                String url = "https://www.x-rates.com/calculator/?from=" + fromCurrency + "&to=" + toCurrency + "&amount=1";

                // Fetch the HTML document
                Document doc = Jsoup.connect(url).get();

                // Extract the conversion rate from the HTML
                String rateText = doc.select(".ccOutputTrail").first().previousSibling().toString(); // Adjust selector based on website structure
                double rate = Double.parseDouble(rateText);

                // Save the scraped rate in the database
                ForexRate forexRate = new ForexRate();
                forexRate.setCurrencyPair(pair.getCurrencyPair());
                forexRate.setDate(LocalDate.now().minusDays(1));
                forexRate.setRate(rate);
                forexRateRepository.save(forexRate);

            } catch (IOException e) {
                // Log error if connection fails
                System.err.println("Failed to fetch rate for " + pair.getCurrencyPair() + ": " + e.getMessage());
            } catch (Exception e) {
                // Handle unexpected parsing or other issues
                System.err.println("Error processing rate for " + pair.getCurrencyPair() + ": " + e.getMessage());
            }
        }
    }
}
