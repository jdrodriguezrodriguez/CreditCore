package com.credito.creditcore.application.installment.port;

import com.credito.creditcore.application.dto.installment.PayInstallmentRequestDto;

public interface PayInstallmentUseCase {
    void payInstallment(Integer installmentId, PayInstallmentRequestDto request);
}