package com.smalaca.orderingservice.infrastructure.pricingservice;

import org.springframework.web.client.RestTemplate;

public class PricingServiceRestClient {
    private final RestTemplate restTemplate;
    private final String url;

    public PricingServiceRestClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public double priceFor(Long customerId, double price) {
        return restTemplate.getForObject(url + "/discount/" + customerId + "/" + price, PriceDto.class).getPrice();
    }
}
