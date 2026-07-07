package com.credito.creditcore.application.installment.port;

import com.credito.creditcore.domain.model.Installment;

public interface GetInstallmentUseCase {
    Installment getInstallment(Integer installmentId);
}
