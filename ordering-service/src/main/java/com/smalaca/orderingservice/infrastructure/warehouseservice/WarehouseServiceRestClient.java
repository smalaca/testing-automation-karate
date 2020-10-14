package com.smalaca.orderingservice.infrastructure.warehouseservice;

import org.springframework.web.client.RestTemplate;

public class WarehouseServiceRestClient {
    private static final String URL = "http://localhost:8200";

    private final RestTemplate restTemplate;

    WarehouseServiceRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ItemDto getItemDto(Long id) {
        return restTemplate.getForObject(URL + "/item/" + id, ItemDto.class);
    }
}
