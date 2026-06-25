package com.credito.creditcore.application.dto.loan;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.LoanType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record LoanSimulationRequestDto(

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be greater than zero")
    BigDecimal loanAmount,

    @Positive(message = "Term in months must be greater than zero")
    int termInMonths,

    @NotNull(message = "Loan type is required")
    LoanType loanType,

    BigDecimal additionalIncome,

    @NotNull(message = "Monthly expenses are required")
    @PositiveOrZero(message = "Monthly expenses cannot be negative")
    BigDecimal monthlyExpenses

) {}