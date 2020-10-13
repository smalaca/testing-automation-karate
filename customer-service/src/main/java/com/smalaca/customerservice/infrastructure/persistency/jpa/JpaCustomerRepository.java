package com.smalaca.customerservice.infrastructure.persistency.jpa;

import com.smalaca.customerservice.domain.customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends CrudRepository<Customer, Long> {
}
