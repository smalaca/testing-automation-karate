package com.smalaca.customerservice.domain.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {
    public Customer create(CustomerDto customerDto) {
        return new Customer(customerDto.getName(), Address.from(customerDto));
    }
}
