package com.smalaca.warehouseservice.domain.item;

public class ItemDto {
    private Long id;
    private String name;
    private double price;
    private String currency;

    ItemDto() {}

    private ItemDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        price = builder.price;
        currency = builder.currency;
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

    static class Builder {
        private final Long id;
        private final String name;
        private double price;
        private String currency;

        private Builder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        static Builder itemDto(Long id, String name) {
            return new Builder(id, name);
        }

        Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        ItemDto build() {
            return new ItemDto(this);
        }
    }
}
