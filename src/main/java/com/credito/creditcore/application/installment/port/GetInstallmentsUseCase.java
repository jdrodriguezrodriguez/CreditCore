package com.credito.creditcore.application.installment.port;

import java.util.List;

import com.credito.creditcore.domain.model.Installment;

public interface GetInstallmentsUseCase {
    List<Installment> getInstallments(Integer loanId);
}
