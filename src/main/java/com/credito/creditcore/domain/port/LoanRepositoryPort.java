package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Loan;

public interface LoanRepositoryPort {
    Optional<Loan> findByCustomerId(Integer customerId);
    Optional<Loan> findByLoanId(Integer loanId);
    void save(Loan loan, Customer customer);
    void update(Loan loan);
} 
