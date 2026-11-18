package com.credito.creditcore.application.installment.port;

import com.credito.creditcore.application.dto.installment.InstallmentResponseDto;

public interface GetInstallmentUseCase {
    InstallmentResponseDto getInstallment(Integer installmentId);
}
