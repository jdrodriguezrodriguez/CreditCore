package com.credito.creditcore.application.dto.loan;

import java.math.BigDecimal;

public record AmortizationResponseDto(
        int installmentNumber,
        BigDecimal initialBalance,
        BigDecimal interest,
        BigDecimal amortization,
        BigDecimal installmentAmount,
        BigDecimal finalBalance) {
}