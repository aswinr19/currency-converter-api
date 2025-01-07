package com.example.currencyconverter.controller;

//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;


import com.example.currencyconverter.model.CurrencyRequest;
import com.example.currencyconverter.model.CurrencyResponse;
import com.example.currencyconverter.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rate")
    public double getExchangeRate(@RequestParam String from, @RequestParam String to) {
        return currencyService.getExchangeRate(from, to);
    }

    @PostMapping("/convert")
    public CurrencyResponse convertCurrency(@RequestBody CurrencyRequest request) {
        double exchangeRate = currencyService.getExchangeRate(request.getFromCurrency(), request.getToCurrency());
        double convertedAmount = currencyService.convertCurrency(request.getAmount(), exchangeRate);
        return new CurrencyResponse(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getAmount(),
                convertedAmount
        );
    }
}

