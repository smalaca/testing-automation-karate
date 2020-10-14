package com.smalaca.orderingservice.infrastructure.warehouse;

import org.springframework.web.client.RestTemplate;

public class WarehouseRestClient {
    private static final String URL = "http://localhost:8200";

    private final RestTemplate restTemplate;

    WarehouseRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ItemDto getItemDto(Long id) {
        return restTemplate.getForObject(URL + "/item/" + id, ItemDto.class);
    }
}
