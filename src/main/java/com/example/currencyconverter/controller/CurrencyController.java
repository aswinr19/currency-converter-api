package com.example.currencyconverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyconverter.model.CurrencyRequest;
import com.example.currencyconverter.model.CurrencyResponse;
import com.example.currencyconverter.service.CurrencyService;

@RestController
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rate")
    public double getExchangeRate(@RequestParam String from, @RequestParam String to) {
        return currencyService.getExchangeRate(sanitizeCurrencyCode(from), sanitizeCurrencyCode(to));
    }

    @PostMapping("/convert")
    public CurrencyResponse convertCurrency(@RequestBody CurrencyRequest request) {

        String fromCurrency = sanitizeCurrencyCode(request.getFromCurrency());
        String toCurrency = sanitizeCurrencyCode(request.getToCurrency());

        double exchangeRate = currencyService.getExchangeRate(fromCurrency, toCurrency);
        double convertedAmount = currencyService.convertCurrency(request.getAmount(), exchangeRate);

        return new CurrencyResponse(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getAmount(),
                convertedAmount
        );
    }

    private String sanitizeCurrencyCode(String currencyCode) {
      if ( currencyCode == null ) 
        throw new IllegalArgumentException("currency code cannot be null!");
     
      
    return currencyCode.trim().toUpperCase();
    }
}

