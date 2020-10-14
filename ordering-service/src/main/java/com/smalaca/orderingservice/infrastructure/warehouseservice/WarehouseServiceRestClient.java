package com.smalaca.orderingservice.infrastructure.warehouseservice;

import org.springframework.web.client.RestTemplate;

public class WarehouseServiceRestClient {
    private final RestTemplate restTemplate;
    private final String url;

    WarehouseServiceRestClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public ItemDto getItemDto(Long id) {
        return restTemplate.getForObject(url + "/item/" + id, ItemDto.class);
    }
}
