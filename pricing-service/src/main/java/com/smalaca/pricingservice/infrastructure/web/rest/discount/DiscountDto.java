package com.smalaca.pricingservice.infrastructure.web.rest.discount;

public class DiscountDto {
    private Long customerId;
    private int percentage;

    public Long getCustomerId() {
        return customerId;
    }

    public int getPercentage() {
        return percentage;
    }
}
