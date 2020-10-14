package com.smalaca.orderingservice.infrastructure.customerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerServiceRestClientFactory {
    @Bean
    public CustomerServiceRestClient customerServiceRestClient(@Value("${customer.service.url}") String url) {
        return new CustomerServiceRestClient(new RestTemplate(), url);
    }
}
