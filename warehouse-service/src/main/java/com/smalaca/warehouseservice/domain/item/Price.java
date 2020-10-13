package com.smalaca.warehouseservice.domain.item;

import javax.persistence.Embeddable;

@Embeddable
class Price {
    private double price;
    private Currency currency;

    private Price() {}

    private Price(double price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    static Price from(ItemDto itemDto) {
        Currency currency = Currency.valueOf(itemDto.getCurrency());
        return new Price(itemDto.getPrice(), currency);
    }

    void fillDto(ItemDto.Builder builder) {
        builder.withPrice(price).withCurrency(currency.name());
    }
}
