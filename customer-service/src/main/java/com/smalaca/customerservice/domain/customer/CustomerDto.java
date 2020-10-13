package com.smalaca.customerservice.domain.customer;

public class CustomerDto {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String postalCode;
    private String street;

    CustomerDto() {}

    private CustomerDto(Builder builder) {
        id = builder.id;
        name = builder.name;
        country = builder.country;
        city = builder.city;
        postalCode = builder.postalCode;
        street = builder.street;
    }

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

    static class Builder {
        private final Long id;
        private final String name;
        private String country;
        private String city;
        private String postalCode;
        private String street;

        private Builder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        static Builder customerDto(Long id, String name) {
            return new Builder(id, name);
        }

        Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        Builder withCity(String city) {
            this.city = city;
            return this;
        }

        Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        CustomerDto build() {
            return new CustomerDto(this);
        }
    }
}
