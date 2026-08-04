package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.installment.PayLateFeeRequestDto;
import com.credito.creditcore.application.installment.port.ProcessLateFeeUseCase;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Payment;
import com.credito.creditcore.domain.model.enums.InstallmentStatus;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.domain.port.PaymentRepositoryPort;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProcessLateFeeService implements ProcessLateFeeUseCase {

    private final InstallmentRepositoryPort installmentRepositoryPort;
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final LateFeeService lateFeeService;

    private static final Logger logger = Logger.getLogger(PayInstallmentService.class.getName());

    public ProcessLateFeeService(
            InstallmentRepositoryPort installmentRepositoryPort, PaymentRepositoryPort paymentRepositoryPort,
            LateFeeService lateFeeService) {
        this.installmentRepositoryPort = installmentRepositoryPort;
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.lateFeeService = lateFeeService;
    }

    @Override
    public void processLateFee(Integer installmentId, PayLateFeeRequestDto request) {

        Installment installment = installmentRepositoryPort.findById(installmentId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Installment not found with ID: " + installmentId));

        lateFeeService.verifyPreviousLateFee(installment);

        if (installment.getStatus() != InstallmentStatus.PARTIALLY) {
            throw new IllegalArgumentException("This installment has no pending late fee.");
        }

        BigDecimal lateFee = lateFeeService.calculateLateFee(installment, request.actualPaymentDate());

        if (request.amountToPay().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than 0");
        }

        if (request.amountToPay().compareTo(lateFee) != 0) {
            throw new IllegalArgumentException(
                    "Amount must be equal than late fee");
        }
        

        installment.setLateFee(lateFee.subtract(request.amountToPay()));
        installment.setStatus(InstallmentStatus.PAID);

        Payment payment = Payment.create(
                installment,
                request.amountToPay(),
                request.paymentMethod(),
                request.paymentConcept());

        installmentRepositoryPort.updateInstallment(installment);
        paymentRepositoryPort.savePayment(payment);

        logger.info("Has paid the late payment fee.");
    }
}
