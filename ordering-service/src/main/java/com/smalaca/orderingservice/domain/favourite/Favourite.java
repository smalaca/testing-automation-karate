package com.smalaca.orderingservice.domain.favourite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Favourite {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private Long itemId;

    private Favourite() {}

    public Favourite(Long customerId, Long itemId) {
        this.customerId = customerId;
        this.itemId = itemId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getItemId() {
        return itemId;
    }
}
