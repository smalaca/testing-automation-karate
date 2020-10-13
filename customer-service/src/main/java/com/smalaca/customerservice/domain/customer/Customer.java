package com.smalaca.customerservice.domain.customer;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static com.smalaca.customerservice.domain.customer.CustomerDto.Builder.customerDto;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private Customer() {}

    Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Long id() {
        return id;
    }

    public void update(CustomerDto customerDto) {
        this.address = Address.from(customerDto);
    }

    public CustomerDto asDto() {
        CustomerDto.Builder builder = customerDto(id, name);
        address.fillDto(builder);

        return builder.build();
    }
}
