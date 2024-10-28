package com.example.forextracker.controller;

import com.example.forextracker.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/forex")
public class ForexController {

    @Autowired
    private ForexService forexService;

    @GetMapping("/average")
    public Map<String, Double> getAverageRate(
            @RequestParam String currencyPair,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        double averageRate = forexService.getAverageRate(currencyPair, start, end);
        Map<String, Double> response = new HashMap<>();
        response.put("averageRate", averageRate);
        return response;
    }

    @GetMapping("/closing")
    public Map<String, Double> getClosingRate(
            @RequestParam String currencyPair,
            @RequestParam String date
    ) {
        LocalDate closingDate = LocalDate.parse(date);
        double closingRate = forexService.getClosingRate(currencyPair, closingDate);
        Map<String, Double> response = new HashMap<>();
        response.put("closingRate", closingRate);
        return response;
    }

    @PostMapping("/addCurrencyPair")
    public Map<String, String> addCurrencyPair(@RequestParam String currencyPair) {
        String result = forexService.addCurrencyPair(currencyPair);
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return response;
    }
}
