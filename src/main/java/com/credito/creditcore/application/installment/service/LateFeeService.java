package com.credito.creditcore.application.installment.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.credito.creditcore.domain.excepcion.LateFeePaymentRequiredException;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;

@Service
public class LateFeeService {

    private final InstallmentRepositoryPort installmentRepositoryPort;

    private static final BigDecimal DAILY_LATE_RATE = new BigDecimal("0.002");

    public LateFeeService(InstallmentRepositoryPort installmentRepositoryPort) {
        this.installmentRepositoryPort = installmentRepositoryPort;
    }

    public void verifyPreviousLateFee(Installment installment) {
        if (installment.getInstallmentNumber() > 1) {
            Integer previousInstallmentNumber = installment.getInstallmentNumber() - 1;

            Installment previousInstallment = installmentRepositoryPort
                    .findByLoanIdAndNumber(installment.getLoan().getLoanId(),
                            previousInstallmentNumber)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Installment not found with Installment Number: "
                                    + previousInstallmentNumber));

            if (previousInstallment.getLateFee() != null
                    && previousInstallment.getLateFee().compareTo(BigDecimal.ZERO) != 0) {
                throw new LateFeePaymentRequiredException(
                        "Must pay the late payment fee. Check the previous installment.");
            }
        }
    }

    // lateFree = amountInstalment × 0.0005 × days
    public BigDecimal calculateLateFee(Installment installment, LocalDate actualPaymentDate) {

        if (actualPaymentDate.compareTo(installment.getDueDate()) > 0) {

            Long daysLate = ChronoUnit.DAYS.between(installment.getDueDate(), actualPaymentDate);

            BigDecimal lateFree = installment.getInstallmentAmount()
                    .multiply(DAILY_LATE_RATE)
                    .multiply(BigDecimal.valueOf(daysLate)).setScale(0, RoundingMode.HALF_UP);

            return lateFree;
        }
        return BigDecimal.ZERO;
    }
}
