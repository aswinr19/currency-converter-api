package com.example.currencyconverter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.currencyconverter.model.CurrencyRequest;
import com.example.currencyconverter.model.CurrencyResponse;
import com.example.currencyconverter.service.CurrencyService;

class CurrencyControllerTest {
    private CurrencyController currencyController;
    private CurrencyService mockCurrencyService;

    @BeforeEach
    void setUp() {
        mockCurrencyService = mock(CurrencyService.class);
        currencyController = new CurrencyController(mockCurrencyService);
    }

    @Test
    void testGetExchangeRateValidInput() {
        when(mockCurrencyService.getExchangeRate("USD", "INR")).thenReturn(0.85);

        double rate = currencyController.getExchangeRate("USD", "INR");
        assertEquals(0.85, rate, 0.01);
        verify(mockCurrencyService, times(1)).getExchangeRate("USD", "INR");
    }

    // @Test
    // void testGetExchangeRateInvalidCurrency() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         currencyController.getExchangeRate("INVALID", "INR");
    //     });

    //     assertTrue(exception.getMessage().contains("Invalid currency code"));
    // }

    @Test
    void testConvertCurrency() {
        CurrencyRequest request = new CurrencyRequest();
        request.setFromCurrency("USD");
        request.setToCurrency("INR");
        request.setAmount(100.0);
        when(mockCurrencyService.getExchangeRate("USD", "INR")).thenReturn(85.835084);
        when(mockCurrencyService.convertCurrency(100.0, 85.835084)).thenReturn(8583.5084);

        CurrencyResponse response = currencyController.convertCurrency(request);

        assertNotNull(response);
        assertEquals("USD", response.getFromCurrency());
        assertEquals("INR", response.getToCurrency());
        assertEquals(100.0, response.getAmount(), 0.01);
        assertEquals(8583.5084, response.getConvertedAmount(), 0.01);

        verify(mockCurrencyService, times(1)).getExchangeRate("USD", "INR");
        verify(mockCurrencyService, times(1)).convertCurrency(100.0, 85.835084);
    }
}
