package com.credito.creditcore.application.installment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.installment.port.GetInstallmentsUseCase;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;

@Service
public class GetInstallmentsService implements GetInstallmentsUseCase {

    private final InstallmentRepositoryPort installmentRepositoryPort;

    public GetInstallmentsService(InstallmentRepositoryPort installmentRepositoryPort) {
        this.installmentRepositoryPort = installmentRepositoryPort;
    }

    @Override
    public List<Installment> getInstallments(Integer loanId) {

        List<Installment> installments =
                installmentRepositoryPort.findByLoanId(loanId);

        if (installments.isEmpty()) {
            throw new IllegalArgumentException(
                    "No installments found for loan ID: " + loanId);
        }

        return installments;
    }
}
