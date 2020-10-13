package com.smalaca.customerservice.infrastructure.web.rest.customer;

import com.smalaca.customerservice.domain.customer.Customer;
import com.smalaca.customerservice.domain.customer.CustomerDto;
import com.smalaca.customerservice.domain.customer.CustomerFactory;
import com.smalaca.customerservice.infrastructure.persistency.jpa.JpaCustomerRepository;
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
@RequestMapping("/customer")
public class CustomerController {
    private final JpaCustomerRepository repository;
    private final CustomerFactory factory;

    public CustomerController(JpaCustomerRepository repository, CustomerFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") long id) {
        Optional<Customer> found = repository.findById(id);

        if (found.isPresent()) {
            return ResponseEntity.ok(found.get().asDto());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<CustomerDto> findAllBy(@RequestParam(required = false) String search) {
        return asCustomerDtos(findAllCustomersBy(search));
    }

    private Stream<Customer> findAllCustomersBy(String search) {
        if (search != null) {
            return repository.findAllByNameContaining(search).stream();
        } else {
            return StreamSupport.stream(repository.findAll().spliterator(), false);
        }
    }

    private List<CustomerDto> asCustomerDtos(Stream<Customer> customers) {
        return customers
                .map(Customer::asDto)
                .collect(toList());
    }

    private Stream<Customer> asStream(Iterable<Customer> customers) {
        return StreamSupport.stream(customers.spliterator(), false);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CustomerDto customerDto) {
        Customer customer = factory.create(customerDto);
        Customer saved = repository.save(customer);

        return ResponseEntity.ok(saved.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody CustomerDto customerDto) {
        Optional<Customer> found = repository.findById(id);

        if (found.isPresent()) {
            Customer customer = found.get();
            customer.update(customerDto);
            repository.save(customer);

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
