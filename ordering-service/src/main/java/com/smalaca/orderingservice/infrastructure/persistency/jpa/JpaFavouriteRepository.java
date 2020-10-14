package com.smalaca.orderingservice.infrastructure.persistency.jpa;

import com.smalaca.orderingservice.domain.favourite.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JpaFavouriteRepository extends CrudRepository<Favourite, Long> {
    List<Favourite> findAllByCustomerId(Long customerId);

    @Transactional
    void deleteAllByCustomerId(Long customerId);
}
