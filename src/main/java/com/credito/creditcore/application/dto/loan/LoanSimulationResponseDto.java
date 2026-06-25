package com.credito.creditcore.application.dto.loan;

import java.math.BigDecimal;
import java.util.List;

import com.credito.creditcore.domain.model.enums.ApprovalEstimate;
import com.credito.creditcore.domain.model.enums.LoanType;

public record LoanSimulationResponseDto(
        BigDecimal loanAmount,
        BigDecimal monthlyInstallment,
        BigDecimal totalAmountPayable,
        BigDecimal totalInterest,
        Integer termInMonths,
        double interestRate,
        int loanScore,
        ApprovalEstimate approvalEstimate,
        LoanType loanType,
        List<AmortizationResponseDto> amortizationResponseDtos
) {
}
