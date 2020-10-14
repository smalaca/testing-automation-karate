package com.smalaca.orderingservice.infrastructure.warehouse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WarehouseRestClientFactory {
    @Bean
    public WarehouseRestClient warehouseRestClient() {
        return new WarehouseRestClient(new RestTemplate());
    }
}
