package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;

public interface CustomerRepositoryPort {
    void save(Customer customer);
    void update(Integer customerId, Customer customer);
    Optional<Customer> findById(Integer customerId);
    Optional<Customer> findByPersonId(Integer personId);
}