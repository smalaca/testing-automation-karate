package com.smalaca.orderingservice.infrastructure.web.rest.offer;

import com.smalaca.orderingservice.infrastructure.warehouse.ItemDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {
    @GetMapping("/{customerId}")
    public List<ItemDto> getAllItems(@PathVariable Long customerId, @RequestParam String search) {
//        - find customer by id
//        - find items by search
//        - get price with discount for customer & month
//        - return list with prices
        return Collections.emptyList();
    }
}
