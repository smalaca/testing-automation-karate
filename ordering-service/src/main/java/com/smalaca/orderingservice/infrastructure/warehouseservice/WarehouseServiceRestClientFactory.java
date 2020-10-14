package com.smalaca.orderingservice.infrastructure.warehouseservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WarehouseServiceRestClientFactory {
    @Bean
    public WarehouseServiceRestClient warehouseRestClient() {
        return new WarehouseServiceRestClient(new RestTemplate());
    }
}
