package com.credito.creditcore.application.event;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanActivateEvent(
    int loanId,
        String recipient,
        BigDecimal principalAmount,
        int termInMonths,
        LocalDate actualPaymentDate
){ 
}
