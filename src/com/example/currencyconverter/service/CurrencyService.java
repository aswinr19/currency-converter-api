package com.example.currencyconverter.service;

import com.example.currencyconverter.utils.ExternalApiClient;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final ExternalApiClient apiClient;

    public CurrencyService(ExternalApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public double getExchangeRate(String fromCurrency, String toCurrency) {
        return apiClient.fetchExchangeRate(fromCurrency, toCurrency);
    }

    public double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}

