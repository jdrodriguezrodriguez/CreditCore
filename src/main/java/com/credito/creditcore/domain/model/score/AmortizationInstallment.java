package com.credito.creditcore.domain.model.score;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmortizationInstallment {

    private int installmentNumber;
    private BigDecimal initialBalance = BigDecimal.ZERO;
    private BigDecimal interest = BigDecimal.ZERO;
    private BigDecimal amortization = BigDecimal.ZERO;
    private BigDecimal installmentAmount = BigDecimal.ZERO;
    private BigDecimal finalBalance = BigDecimal.ZERO;

    public AmortizationInstallment() {
    }

    public AmortizationInstallment(
            int installmentNumber,
            BigDecimal initialBalance,
            BigDecimal interest,
            BigDecimal amortization,
            BigDecimal installmentAmount,
            BigDecimal finalBalance) {

        this.installmentNumber = installmentNumber;
        this.initialBalance = initialBalance;
        this.interest = interest;
        this.amortization = amortization;
        this.installmentAmount = installmentAmount;
        this.finalBalance = finalBalance;
    }
}