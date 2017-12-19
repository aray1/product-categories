package com.aray1.commonproblems.productmanagement.product.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pricing {

    @NotNull
    @JsonProperty
    private Double price;

    @NotNull
    @JsonProperty
    private String currency;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
