package com.example.currencyconverter.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

   // @Value("${exchange.api.url}")

    @Value("https://openexchangerates.org/api/latest.json?app_id=a5561add2f7a45bfa69886c43598770e")
    private String apiUrl; 
    public double fetchExchangeRate(String fromCurrency, String toCurrency) {

       // the base currency is USD by default, so no need to set it here 
        String url = apiUrl + "&base=" + fromCurrency; 

      System.out.println(url);
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response != null && response.getRates().containsKey(toCurrency)) {
            return response.getRates().get(toCurrency);
        } else {
            throw new IllegalArgumentException("Exchange rate not found for " + fromCurrency + " to " + toCurrency);
        }
    }
}

class ExchangeRateResponse {
    private String disclaimer;
    private String license;
    private long timestamp;
    private String base;
    private Map<String, Double> rates;

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

