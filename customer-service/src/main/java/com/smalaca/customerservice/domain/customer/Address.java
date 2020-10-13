package com.smalaca.customerservice.domain.customer;

import javax.persistence.Embeddable;

@Embeddable
class Address {
    private String street;
    private String postalCode;
    private String city;
    private String country;

    private Address() {}

    private Address(String street, String postalCode, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    static Address from(CustomerDto customerDto) {
        return new Address(customerDto.getStreet(), customerDto.getPostalCode(), customerDto.getCity(), customerDto.getCountry());
    }


    void fillDto(CustomerDto.Builder builder) {
        builder
                .withStreet(street)
                .withPostalCode(postalCode)
                .withCity(city)
                .withCountry(country);
    }
}
