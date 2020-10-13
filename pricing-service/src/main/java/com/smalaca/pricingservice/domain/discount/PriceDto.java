package com.smalaca.pricingservice.domain.discount;

public class PriceDto {
    private final double price;

    PriceDto(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
