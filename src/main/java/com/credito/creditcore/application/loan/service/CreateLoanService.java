package com.credito.creditcore.application.loan.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.loan.LoanCreationRequestDto;
import com.credito.creditcore.application.loan.port.CreateLoanUseCase;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.domain.port.LoanRepositoryPort;

@Service
public class CreateLoanService implements CreateLoanUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final LoanRepositoryPort loanRepositoryPort;

    public CreateLoanService(CustomerRepositoryPort customerRepositoryPort,
            LoanRepositoryPort loanRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.loanRepositoryPort = loanRepositoryPort;
    }

    @Override
    public Loan createLoan(LoanCreationRequestDto request, Integer personId) {

        Customer customer = customerRepositoryPort.findByPersonId(personId).orElseThrow(
                () -> new IllegalArgumentException("Customer not found with person ID: " + personId));

        Loan loan = Loan.create(
                customer,
                request.loanAmount(),
                request.interestRate(),
                request.termMonths(),
                LoanStatus.REQUESTED,
                LocalDate.now(),
                null,
                request.loanType(),
                request.totalInterest(),
                request.totalAmountPayable(),
                BigDecimal.ZERO,
                request.loanAmount());

        loanRepositoryPort.save(loan, customer);

        return loan;
    }
}
