package com.smalaca.orderingservice.infrastructure.customerservice;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CustomerServiceRestClient {
    private final RestTemplate restTemplate;
    private final String url;

    CustomerServiceRestClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public boolean existsById(Long customerId) {
        try {
            CustomerDto dto = restTemplate.getForObject(url + "/customer/" + customerId, CustomerDto.class);
            return dto != null;
        } catch (HttpClientErrorException.NotFound notFound) {
            return false;
        }
    }
}
