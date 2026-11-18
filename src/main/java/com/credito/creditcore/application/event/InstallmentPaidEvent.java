package com.credito.creditcore.application.event;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.InstallmentStatus;
import com.credito.creditcore.domain.model.enums.PaymentMethod;

public record InstallmentPaidEvent(
        int installmentNumber,
        String recipient,
        InstallmentStatus status,
        BigDecimal paidAmount,
        PaymentMethod paymentMethod,
        LocalDate actualPaymentDate)
{
}
