package com.credito.creditcore.domain.port;

import java.math.BigDecimal;
import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;

public interface CustomerRepositoryPort {
    
    void save(Customer customer);
    void updateSalary(Integer customerId, BigDecimal salary);
    void updateCreditScore(Integer customerId, int score);
    Optional<Customer> findById(Integer customerId);
    Optional<Customer> findByPersonId(Integer personId);
}