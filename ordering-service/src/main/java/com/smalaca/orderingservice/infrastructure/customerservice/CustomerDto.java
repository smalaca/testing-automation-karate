package com.smalaca.orderingservice.infrastructure.customerservice;

public class CustomerDto {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String postalCode;
    private String street;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }
}
