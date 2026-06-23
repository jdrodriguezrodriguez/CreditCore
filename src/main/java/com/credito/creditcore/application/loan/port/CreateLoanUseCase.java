package com.credito.creditcore.application.loan.port;

import com.credito.creditcore.application.dto.prestamo.LoanCreationRequestDto;
import com.credito.creditcore.domain.model.Loan;

public interface CreateLoanUseCase {
    Loan createLoan(LoanCreationRequestDto request, Integer personId);
}