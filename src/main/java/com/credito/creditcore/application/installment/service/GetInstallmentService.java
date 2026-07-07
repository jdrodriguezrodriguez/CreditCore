package com.credito.creditcore.application.installment.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.installment.port.GetInstallmentUseCase;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;

@Service
public class GetInstallmentService implements GetInstallmentUseCase{

    private final InstallmentRepositoryPort installmentRepositoryPort;

    public GetInstallmentService(InstallmentRepositoryPort installmentRepositoryPort) {
        this.installmentRepositoryPort = installmentRepositoryPort;
    }

    @Override
    public Installment getInstallment(Integer installmentId) {

        Installment installment = installmentRepositoryPort.findById(installmentId)
            .orElseThrow(() -> new IllegalArgumentException("Not found installment with loan ID: " + installmentId));

        return installment;
    }
}
