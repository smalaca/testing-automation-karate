package com.smalaca.orderingservice.infrastructure.web.rest.favourites;

import com.smalaca.orderingservice.domain.favourite.Favourite;
import com.smalaca.orderingservice.infrastructure.customerservice.CustomerServiceRestClient;
import com.smalaca.orderingservice.infrastructure.persistency.jpa.JpaFavouriteRepository;
import com.smalaca.orderingservice.infrastructure.warehouseservice.ItemDto;
import com.smalaca.orderingservice.infrastructure.warehouseservice.WarehouseServiceRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/favourites")
public class FavouritesController {
    private final JpaFavouriteRepository repository;
    private final WarehouseServiceRestClient warehouseServiceRestClient;
    private final CustomerServiceRestClient customerServiceRestClient;

    public FavouritesController(
            JpaFavouriteRepository repository, WarehouseServiceRestClient warehouseServiceRestClient, CustomerServiceRestClient customerServiceRestClient) {
        this.repository = repository;
        this.warehouseServiceRestClient = warehouseServiceRestClient;
        this.customerServiceRestClient = customerServiceRestClient;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ItemDto>> favouritesOf(@PathVariable Long customerId) {
        if (customerServiceRestClient.existsById(customerId)) {
            List<Favourite> favourites = repository.findAllByCustomerId(customerId);

            List<ItemDto> items = favourites.stream()
                    .map(favourite -> getItemDtoFor(favourite.getItemId()))
                    .collect(toList());

            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ItemDto getItemDtoFor(Long itemId) {
        Optional<ItemDto> itemDto = warehouseServiceRestClient.getItemDto(itemId);

        if (itemDto.isPresent()) {
            return itemDto.get();
        } else {
            return ItemDto.notFound(itemId);
        }
    }

    @PostMapping("/{customerId}/{itemId}")
    public void addFavourite(@PathVariable Long customerId, @PathVariable Long itemId) {
        repository.save(new Favourite(customerId, itemId));
    }

    @DeleteMapping("/{customerId}")
    public void deleteAllFor(@PathVariable Long customerId) {
        repository.deleteAllByCustomerId(customerId);
    }
}
