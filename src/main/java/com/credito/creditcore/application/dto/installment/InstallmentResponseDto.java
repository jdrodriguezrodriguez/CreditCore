package com.credito.creditcore.application.dto.installment;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.application.dto.loan.LoanResponseDto;
import com.credito.creditcore.domain.model.enums.InstallmentStatus;

public record InstallmentResponseDto(

        Integer installmentId,
        Integer installmentNumber,

        BigDecimal installmentAmount,

        LocalDate dueDate,
        LocalDate actualPaymentDate,

        InstallmentStatus status,

        BigDecimal initialBalance,
        BigDecimal interest,
        BigDecimal capitalAmortization,
        BigDecimal finalBalance,

        BigDecimal paidAmount,
        BigDecimal lateFee,

        LoanResponseDto loan
) {
}