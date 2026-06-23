package com.credito.creditcore.application.loan.port;

import com.credito.creditcore.domain.model.Loan;

public interface GetLoanUseCase {
    Loan getLoan(Integer personId);
} 