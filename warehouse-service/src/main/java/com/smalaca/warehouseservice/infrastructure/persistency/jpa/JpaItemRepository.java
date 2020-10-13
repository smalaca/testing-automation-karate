package com.smalaca.warehouseservice.infrastructure.persistency.jpa;

import com.smalaca.warehouseservice.domain.item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByNameContaining(String namePart);
}
