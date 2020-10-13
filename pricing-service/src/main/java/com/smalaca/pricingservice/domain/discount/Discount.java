package com.smalaca.pricingservice.domain.discount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Discount {
    public static final Discount NO_DISCOUNT = new Discount(null, 0);
    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;
    private int percentage;

    private Discount() {}

    public Discount(Long customerId, int percentage) {
        this.customerId = customerId;
        this.percentage = percentage;
    }

    public static Discount noDiscount() {
        return NO_DISCOUNT;
    }

    public PriceDto applyTo(double price) {
        if (percentage != 0) {
            return new PriceDto(withDiscount(price));
        } else {
            return new PriceDto(price);
        }
    }

    private double withDiscount(double price) {
        double discount = percentage / 100.0 * price;

        return price - discount;
    }

    public Long id() {
        return id;
    }
}
