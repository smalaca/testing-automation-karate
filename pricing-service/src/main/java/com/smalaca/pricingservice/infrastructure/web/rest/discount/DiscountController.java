package com.smalaca.pricingservice.infrastructure.web.rest.discount;

import com.smalaca.pricingservice.domain.discount.Discount;
import com.smalaca.pricingservice.domain.discount.PriceDto;
import com.smalaca.pricingservice.infrastructure.persistency.jpa.JpaDiscountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final JpaDiscountRepository repository;

    public DiscountController(JpaDiscountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{customerId}/{price}")
    public ResponseEntity<PriceDto> getPriceFor(@PathVariable long customerId, @PathVariable double price) {
        Discount discount = repository.findByCustomerId(customerId)
                .orElseGet(Discount::noDiscount);

        return ResponseEntity.ok(discount.applyTo(price));
    }

    @PostMapping
    public ResponseEntity<Long> createDiscount(@RequestBody DiscountDto discountDto) {
        Optional<Discount> found = repository.findByCustomerId(discountDto.getCustomerId());

        if (found.isPresent()) {
            Discount discount = found.get();
            discount.changePercentage(discountDto.getPercentage());
            repository.save(discount);

            return ResponseEntity.ok(discount.id());
        } else {
            Discount discount = new Discount(discountDto.getCustomerId(), discountDto.getPercentage());
            return ResponseEntity.ok(repository.save(discount).id());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        repository.deleteAll();
    }
}
