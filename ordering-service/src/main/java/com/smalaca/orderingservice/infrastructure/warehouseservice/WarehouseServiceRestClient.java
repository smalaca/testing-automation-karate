package com.smalaca.orderingservice.infrastructure.warehouseservice;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class WarehouseServiceRestClient {
    private final RestTemplate restTemplate;
    private final String url;

    WarehouseServiceRestClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public Optional<ItemDto> getItemDto(Long id) {
        try {
            return Optional.of(restTemplate.getForObject(url + "/item/" + id, ItemDto.class));
        } catch (HttpClientErrorException.NotFound notFound) {
            return Optional.empty();
        }
    }
}
