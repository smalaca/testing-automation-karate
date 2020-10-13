package com.smalaca.warehouseservice.domain.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static com.smalaca.warehouseservice.domain.item.ItemDto.Builder.itemDto;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Price price;

    private Item() {}

    Item(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Long id() {
        return id;
    }

    public void update(ItemDto itemDto) {
        this.price = Price.from(itemDto);
    }

    public ItemDto asDto() {
        ItemDto.Builder builder = itemDto(id, name);
        price.fillDto(builder);

        return builder.build();
    }
}
