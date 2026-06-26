package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.port.LoanRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.CustomerMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.LoanMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonMapperOut;
import com.credito.creditcore.infrastructure.entity.CustomerEntity;
import com.credito.creditcore.infrastructure.entity.PersonEntity;
import com.credito.creditcore.infrastructure.entity.LoanEntity;
import com.credito.creditcore.infrastructure.persistence.LoanRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class LoanRepositoryAdapter implements LoanRepositoryPort {

    private final LoanRepositoryJpa repositoryJpa;

    public LoanRepositoryAdapter(LoanRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Optional<Loan> findByCustomerId(Integer customerId) {
        return repositoryJpa.findByCustomer_CustomerId(customerId)
                .map(loan -> {
                    return LoanMapperOut.toDomain(loan);
                });
    }

    @Override
    public void save(Loan loan, Customer customer) {
        PersonEntity personEntity = PersonMapperOut.toEntity(customer.getPerson());
        CustomerEntity customerEntity = CustomerMapperOut.toEntity(customer, personEntity);
        LoanEntity loanEntity = LoanMapperOut.toEntity(loan, customerEntity);

        repositoryJpa.save(loanEntity);
    }

    @Override
    public void update(Loan loan) {
        LoanEntity loanEntity = repositoryJpa.findById(loan.getLoanId()).orElseThrow(
                () -> new EntityNotFoundException());
        loanEntity = LoanMapperOut.updateEntity(loanEntity, loan);

        repositoryJpa.save(loanEntity);
    }

    @Override
    public Optional<Loan> findByLoanId(Integer loanId) {
        return repositoryJpa.findById(loanId).map(loan -> {
            return LoanMapperOut.toDomain(loan);
        });
    }
}
