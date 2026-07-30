package com.credito.creditcore.application.loan.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.loan.port.GetLoanUseCase;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.port.LoanRepositoryPort;

@Service
public class GetLoanService implements GetLoanUseCase {

    private final LoanRepositoryPort loanRepositoryPort;

    public GetLoanService(LoanRepositoryPort loanRepositoryPort) {
        this.loanRepositoryPort = loanRepositoryPort;
    }

    @Override
    public Loan getLoan(Integer loanId) {
        return loanRepositoryPort.findByLoanId(loanId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Loan not found for loan ID: " + loanId));
    }
}
