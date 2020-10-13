package com.smalaca.warehouseservice.domain.item;

import org.springframework.stereotype.Component;

@Component
public class ItemFactory {
    public Item create(ItemDto itemDto) {
        return new Item(itemDto.getName(), Price.from(itemDto));
    }
}
