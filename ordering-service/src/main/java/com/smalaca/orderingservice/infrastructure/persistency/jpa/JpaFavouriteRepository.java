package com.smalaca.orderingservice.infrastructure.persistency.jpa;

import com.smalaca.orderingservice.domain.favourite.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFavouriteRepository extends CrudRepository<Favourite, Long> {
    void deleteAllByCustomerId(Long customerId);
}
