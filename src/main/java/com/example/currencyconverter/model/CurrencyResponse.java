package com.example.currencyconverter.model;

public class CurrencyResponse {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double convertedAmount;

    public CurrencyResponse(String fromCurrency, String toCurrency, double amount, double convertedAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }

    public String getFromCurrency() { return fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public double getAmount() { return amount; }
    public double getConvertedAmount() { return convertedAmount; }
}

