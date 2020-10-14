package com.smalaca.orderingservice.infrastructure.warehouseservice;

public class ItemDto {
    private Long id;
    private String name;
    private double price;
    private String currency;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
