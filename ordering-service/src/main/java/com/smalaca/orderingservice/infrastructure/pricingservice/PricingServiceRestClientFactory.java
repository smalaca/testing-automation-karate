package com.smalaca.orderingservice.infrastructure.pricingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PricingServiceRestClientFactory {
    @Bean
    public PricingServiceRestClient pricingServiceRestClient(@Value("${pricing.service.url}") String url) {
        return new PricingServiceRestClient(new RestTemplate(), url);
    }
}
