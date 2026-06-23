package com.credito.creditcore.application.dto.prestamo;

import java.math.BigDecimal;
import java.util.List;

import com.credito.creditcore.domain.model.enums.ApprovalEstimate;
import com.credito.creditcore.domain.model.enums.LoanType;

public record LoanCreationRequestDto(

        BigDecimal loanAmount,
        BigDecimal monthlyInstallment,
        BigDecimal totalAmountPayable,
        BigDecimal totalInterest,
        Integer termMonths,
        double interestRate,
        int creditScore,
        ApprovalEstimate riskLevel,
        LoanType loanType,
        List<AmortizationResponseDto> amortizationDetails) {

}