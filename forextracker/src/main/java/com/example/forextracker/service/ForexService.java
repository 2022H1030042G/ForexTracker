package com.example.forextracker.service;

import com.example.forextracker.model.ForexRate;
import com.example.forextracker.model.TrackedCurrencyPair;
import com.example.forextracker.repository.ForexRateRepository;
import com.example.forextracker.repository.TrackedCurrencyPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ForexService {

    @Autowired
    private ForexRateRepository forexRateRepository;

    @Autowired
    private TrackedCurrencyPairRepository trackedCurrencyPairRepository;

    public double getAverageRate(String currencyPair, LocalDate startDate, LocalDate endDate) {
        List<ForexRate> rates = forexRateRepository.findByCurrencyPairAndDateBetween(currencyPair, startDate, endDate);
        return rates.stream().mapToDouble(ForexRate::getRate).average().orElse(0);
    }

    public double getClosingRate(String currencyPair, LocalDate date) {
        ForexRate rate = forexRateRepository.findTopByCurrencyPairAndDateOrderByDateDesc(currencyPair, date);
        return rate != null ? rate.getRate() : 0;
    }

    public String addCurrencyPair(String currencyPair) {
        if (trackedCurrencyPairRepository.existsByCurrencyPair(currencyPair)) {
            return "Currency pair is already being tracked.";
        }
        TrackedCurrencyPair newPair = new TrackedCurrencyPair(currencyPair);
        trackedCurrencyPairRepository.save(newPair);
        return "Currency pair " + currencyPair + " added for tracking.";
    }
}
