package com.credito.creditcore.application.loan.port;

import com.credito.creditcore.application.dto.loan.LoanSimulationRequestDto;
import com.credito.creditcore.application.dto.loan.LoanSimulationResponseDto;

public interface SimulateLoanUseCase {
    LoanSimulationResponseDto simulateLoan(LoanSimulationRequestDto request, Integer personId);
}
