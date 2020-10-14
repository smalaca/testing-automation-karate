package com.smalaca.orderingservice.infrastructure.web.rest.favourites;

import com.smalaca.orderingservice.domain.favourite.Favourite;
import com.smalaca.orderingservice.infrastructure.persistency.jpa.JpaFavouriteRepository;
import com.smalaca.orderingservice.infrastructure.warehouseservice.ItemDto;
import com.smalaca.orderingservice.infrastructure.warehouseservice.WarehouseServiceRestClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/favourites")
public class FavouritesController {
    private final JpaFavouriteRepository repository;
    private final WarehouseServiceRestClient warehouseServiceRestClient;

    public FavouritesController(JpaFavouriteRepository repository, WarehouseServiceRestClient warehouseServiceRestClient) {
        this.repository = repository;
        this.warehouseServiceRestClient = warehouseServiceRestClient;
    }

    @GetMapping("/{customerId}")
    public List<ItemDto> favouritesOf(@PathVariable Long customerId) {
        // check if customer exists
        List<Favourite> favourites = repository.findAllByCustomerId(customerId);

        return favourites.stream()
                .map(favourite -> warehouseServiceRestClient.getItemDto(favourite.getItemId()))
                .collect(toList());
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