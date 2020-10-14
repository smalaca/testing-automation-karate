package com.smalaca.orderingservice.infrastructure.warehouseservice;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

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

    public List<ItemDto> findByNameContaining(String namePart) {
        URI uri = UriComponentsBuilder.fromHttpUrl(url + "/item")
                .queryParam("search", namePart)
                .build().toUri();

        return asList(restTemplate.getForObject(uri, ItemDto[].class));
    }
}
