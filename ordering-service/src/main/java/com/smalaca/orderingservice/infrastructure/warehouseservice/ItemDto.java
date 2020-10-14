package com.smalaca.orderingservice.infrastructure.warehouseservice;

public class ItemDto {
    private Long id;
    private String name;
    private double price;
    private String currency;

    public ItemDto() {}

    private ItemDto(Long id, String name, int price, String currency) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public static ItemDto notFound(Long itemId) {
        return new ItemDto(itemId, "NOT FOUND", 0, "PLN");
    }

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
