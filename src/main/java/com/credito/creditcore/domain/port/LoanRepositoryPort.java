package com.credito.creditcore.domain.port;

import java.math.BigDecimal;
import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.model.enums.LoanStatus;

public interface LoanRepositoryPort {
    Optional<Loan> findByCustomerId(Integer customerId);
    Optional<Loan> findByLoanId(Integer loanId);
    void save(Loan loan, Customer customer);
    void update(Loan loan);
    void updateTotalPaid(BigDecimal paidAmount, Integer loanId);
    void updateLoanStatus(LoanStatus loanStatus, Integer loanId);
} 
