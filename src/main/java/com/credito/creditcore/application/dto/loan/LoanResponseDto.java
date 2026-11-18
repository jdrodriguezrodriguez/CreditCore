package com.credito.creditcore.application.dto.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.model.enums.LoanType;

public record LoanResponseDto(

        Integer loanId,

        BigDecimal principalAmount,
        BigDecimal interestRate,

        Integer termInMonths,

        BigDecimal totalAmountDue,
        BigDecimal totalInterest,
        BigDecimal totalPaid,
        BigDecimal outstandingBalance,

        LoanStatus loanStatus,
        LoanType loanType,

        LocalDate applicationDate,
        LocalDate approvalDate

) {
}