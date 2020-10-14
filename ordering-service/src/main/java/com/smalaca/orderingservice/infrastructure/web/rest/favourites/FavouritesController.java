package com.smalaca.orderingservice.infrastructure.web.rest.favourites;

import com.smalaca.orderingservice.domain.favourite.Favourite;
import com.smalaca.orderingservice.infrastructure.persistency.jpa.JpaFavouriteRepository;
import com.smalaca.orderingservice.infrastructure.warehouse.ItemDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavouritesController {
    private final JpaFavouriteRepository repository;

    public FavouritesController(JpaFavouriteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{customerId}")
    public List<ItemDto> favouritesOf(@PathVariable Long customerId) {
//        - find customer by id
//        - find favourite items for customer
        return Collections.emptyList();
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
