package com.credito.creditcore.application.event;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.LoanStatus;

public record LoanCompletedEvent(
        int loanId,
        String recipient,
        LoanStatus status,
        BigDecimal totalPaid,
        LocalDate actualPaymentDate) {

}
