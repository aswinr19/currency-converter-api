package com.example.currencyconverter.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ExternalApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${exchange.api.url}")
    private String apiUrl; 
    public double fetchExchangeRate(String fromCurrency, String toCurrency) {
        String url = apiUrl + "?base=" + fromCurrency; 
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response != null && response.getRates().containsKey(toCurrency)) {
            return response.getRates().get(toCurrency);
        } else {
            throw new IllegalArgumentException("Exchange rate not found for " + fromCurrency + " to " + toCurrency);
        }
    }
}

// Response model for external API
class ExchangeRateResponse {
    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, Double> rates;

    // Getters and Setters
    public String getDisclaimer() { return disclaimer; }
    public void setDisclaimer(String disclaimer) { this.disclaimer = disclaimer; }
    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public String getBase() { return base; }
    public void setBase(String base) { this.base = base; }
    public Map<String, Double> getRates() { return rates; }
    public void setRates(Map<String, Double> rates) { this.rates = rates; }
}

