package com.credito.creditcore.application.loan.port;

import com.credito.creditcore.application.dto.prestamo.LoanSimulationRequestDto;
import com.credito.creditcore.application.dto.prestamo.LoanSimulationResponseDto;

public interface SimulateLoanUseCase {
    LoanSimulationResponseDto simulateLoan(LoanSimulationRequestDto request, Integer personId);
}
