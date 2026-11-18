package com.credito.creditcore.application.installment.port;

import java.util.List;

import com.credito.creditcore.application.dto.installment.InstallmentResponseDto;

public interface GetInstallmentsUseCase {
    List<InstallmentResponseDto> getInstallments(Integer loanId);
}
