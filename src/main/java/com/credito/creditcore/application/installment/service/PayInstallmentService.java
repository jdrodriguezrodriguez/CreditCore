package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.installment.PayInstallmentRequestDto;
import com.credito.creditcore.application.installment.port.PayInstallmentUseCase;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.domain.port.PaymentRepositoryPort;

@Service
public class PayInstallmentService implements PayInstallmentUseCase {

    private final InstallmentRepositoryPort installmentRepositoryPort;
    private final PaymentRepositoryPort paymentRepositoryPort;

    public PayInstallmentService(
            InstallmentRepositoryPort installmentRepositoryPort,
            PaymentRepositoryPort paymentRepositoryPort) {
        this.installmentRepositoryPort = installmentRepositoryPort;
        this.paymentRepositoryPort = paymentRepositoryPort;
    }

    @Override
    public void payInstallment(
            Integer installmentId,
            PayInstallmentRequestDto request) {

        Installment installment = installmentRepositoryPort.findById(installmentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Installment not found with ID: " + installmentId));

        if (request.amountToPay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0");
        }

        installmentRepositoryPort.updateInstallment(
                installmentId,
                request.amountToPay());

        paymentRepositoryPort.savePayment(
                installment,
                request.paymentMethod(),
                request.amountToPay());
    }
}