package com.smalaca.orderingservice.infrastructure.web.rest.offer;

import com.smalaca.orderingservice.infrastructure.customerservice.CustomerDto;
import com.smalaca.orderingservice.infrastructure.customerservice.CustomerServiceRestClient;
import com.smalaca.orderingservice.infrastructure.pricingservice.PricingServiceRestClient;
import com.smalaca.orderingservice.infrastructure.warehouseservice.ItemDto;
import com.smalaca.orderingservice.infrastructure.warehouseservice.WarehouseServiceRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offer")
public class OfferController {
    private final CustomerServiceRestClient customerServiceRestClient;
    private final WarehouseServiceRestClient warehouseServiceRestClient;
    private final PricingServiceRestClient pricingServiceRestClient;

    public OfferController(
            CustomerServiceRestClient customerServiceRestClient, WarehouseServiceRestClient warehouseServiceRestClient,
            PricingServiceRestClient pricingServiceRestClient) {
        this.customerServiceRestClient = customerServiceRestClient;
        this.warehouseServiceRestClient = warehouseServiceRestClient;
        this.pricingServiceRestClient = pricingServiceRestClient;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ItemDto>> getAllItems(@PathVariable Long customerId, @RequestParam String search) {
        Optional<CustomerDto> found = customerServiceRestClient.findById(customerId);

        if (found.isPresent()) {
            List<ItemDto> items = warehouseServiceRestClient.findByNameContaining(search);

            List<ItemDto> withDiscount = items.stream()
                    .map(itemDto -> itemDto.withPrice(pricingServiceRestClient.priceFor(customerId, itemDto.getPrice())))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(withDiscount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
