package com.credito.creditcore.application.event;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LateFeePaidEvent(
        int installmentNumber,
        String recipient,
        BigDecimal paidAmount,
        LocalDate actualPaymentDate) {
}
