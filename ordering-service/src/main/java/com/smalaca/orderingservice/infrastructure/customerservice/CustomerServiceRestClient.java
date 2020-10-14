package com.smalaca.orderingservice.infrastructure.customerservice;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class CustomerServiceRestClient {
    private final RestTemplate restTemplate;
    private final String url;

    CustomerServiceRestClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public boolean existsById(Long customerId) {
        return findById(customerId).isPresent();
    }

    public Optional<CustomerDto> findById(Long customerId) {
        try {
            return Optional.of(restTemplate.getForObject(url + "/customer/" + customerId, CustomerDto.class));
        } catch (HttpClientErrorException.NotFound notFound) {
            return Optional.empty();
        }
    }
}
