package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.installment.InstallmentResponseDto;
import com.credito.creditcore.application.installment.port.GetInstallmentUseCase;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;

@Service
public class GetInstallmentService implements GetInstallmentUseCase {

    private final InstallmentRepositoryPort installmentRepositoryPort;
    private final LateFeeService lateFeeService;

    public GetInstallmentService(InstallmentRepositoryPort installmentRepositoryPort, LateFeeService lateFeeService) {
        this.installmentRepositoryPort = installmentRepositoryPort;
        this.lateFeeService = lateFeeService;
    }

    @Override
    public InstallmentResponseDto getInstallment(Integer installmentId) {

        Installment installment = installmentRepositoryPort.findById(installmentId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Not found installment with loan ID: " + installmentId));

        BigDecimal lateFree = lateFeeService.calculateLateFee(installment, LocalDate.now());    //CAMBIAR FECHA PARA TEST

        return new InstallmentResponseDto(
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
                lateFree,
                null);
    }
}
