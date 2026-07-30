package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.installment.InstallmentResponseDto;
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
    public List<InstallmentResponseDto> getInstallments(Integer loanId) {

        List<Installment> installments = installmentRepositoryPort.findByLoanId(loanId);

        if (installments.isEmpty()) {
            throw new IllegalArgumentException(
                    "No installments found for loan ID: " + loanId);
        }

        List<InstallmentResponseDto> installmentsResponse = installments.stream().map(
                installment -> new InstallmentResponseDto(
                        installment.getInstallmentId(),
                        installment.getInstallmentNumber(),
                        installment.getInstallmentAmount(),
                        installment.getDueDate(),
                        installment.getActualPaymentDate(),
                        installment.getStatus(),
                        installment.getInitialBalance(),
                        installment.getInterest(),
                        installment.getCapitalAmortization(),
                        installment.getFinalBalance(),
                        installment.getPaidAmount(),
                        null,
                        null))
                .toList();

        return installmentsResponse;
    }
}
