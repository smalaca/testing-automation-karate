package com.smalaca.pricingservice.infrastructure.persistency.jpa;

import com.smalaca.pricingservice.domain.discount.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface JpaDiscountRepository extends CrudRepository<Discount, Long> {
    Optional<Discount> findByCustomerId(long customerId);

    @Transactional
    void deleteAllByCustomerId(long customerId);
}
