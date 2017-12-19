package com.aray1.commonproblems.productmanagement.product.service;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aray1.commonproblems.productmanagement.exception.ProductCategoriesException;
import com.aray1.commonproblems.productmanagement.product.model.CurrencyExchange;

@Service
public class CurrencyService {

    @Value("${currency.exchange.api.path}")
    private String currencyExchangePath;

    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void validateCurrency(String currency) {
        try {
            Currency.getInstance(currency);
        } catch (final IllegalArgumentException e) {
            throw new ProductCategoriesException("Invalid currency");
        }
    }

    public Double getExchangeRate(String baseCurrency, String evaluatingCurrency) {
        Objects.requireNonNull(baseCurrency);
        Objects.requireNonNull(evaluatingCurrency);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("base", baseCurrency.toUpperCase());
        paramMap.put("symbols", evaluatingCurrency.toUpperCase());
        ResponseEntity<CurrencyExchange> forEntity =
                restTemplate.getForEntity(currencyExchangePath, CurrencyExchange.class, paramMap);
        return forEntity.getBody().getRates().get(evaluatingCurrency);
    }

    public Double getExchangeRate(String evaluatingCurrency) {
        Objects.requireNonNull(evaluatingCurrency);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("base", "EUR");
        paramMap.put("symbols", evaluatingCurrency.toUpperCase());
        ResponseEntity<CurrencyExchange> forEntity =
                restTemplate.getForEntity(currencyExchangePath, CurrencyExchange.class, paramMap);
        return forEntity.getBody().getRates().get(evaluatingCurrency);
    }

}
