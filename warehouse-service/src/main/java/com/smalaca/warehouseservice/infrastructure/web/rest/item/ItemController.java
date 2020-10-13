package com.smalaca.warehouseservice.infrastructure.web.rest.item;

import com.smalaca.warehouseservice.domain.item.Item;
import com.smalaca.warehouseservice.domain.item.ItemDto;
import com.smalaca.warehouseservice.domain.item.ItemFactory;
import com.smalaca.warehouseservice.infrastructure.persistency.jpa.JpaItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final JpaItemRepository repository;
    private final ItemFactory factory;

    public ItemController(JpaItemRepository repository, ItemFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable("id") long id) {
        Optional<Item> found = repository.findById(id);

        if (found.isPresent()) {
            return ResponseEntity.ok(found.get().asDto());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<ItemDto> findAllBy(@RequestParam(required = false) String search) {
        return asItemDtos(findAllItemsBy(search));
    }

    private Stream<Item> findAllItemsBy(String search) {
        if (search != null) {
            return repository.findAllByNameContaining(search).stream();
        } else {
            return StreamSupport.stream(repository.findAll().spliterator(), false);
        }
    }

    private List<ItemDto> asItemDtos(Stream<Item> items) {
        return items
                .map(Item::asDto)
                .collect(toList());
    }

    private Stream<Item> asStream(Iterable<Item> items) {
        return StreamSupport.stream(items.spliterator(), false);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ItemDto itemDto) {
        Item item = factory.create(itemDto);
        Item saved = repository.save(item);

        return ResponseEntity.ok(saved.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ItemDto itemDto) {
        Optional<Item> found = repository.findById(id);

        if (found.isPresent()) {
            Item item = found.get();
            item.update(itemDto);
            repository.save(item);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAll() {
        repository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
