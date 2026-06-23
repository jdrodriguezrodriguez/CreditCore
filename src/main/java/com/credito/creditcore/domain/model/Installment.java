package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.InstallmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Installment {

    private Integer installmentId;
    private Loan loan;

    private int installmentNumber;
    private LocalDate dueDate;
    private InstallmentStatus status;

    private BigDecimal initialBalance;
    private BigDecimal interest;
    private BigDecimal capitalAmortization;
    private BigDecimal installmentAmount;
    private BigDecimal finalBalance;

    private BigDecimal paidAmount;
    private BigDecimal lateFee;
    private LocalDate actualPaymentDate;

    public Installment() {
    }

    public Installment(
            Integer installmentId,
            Loan loan, int installmentNumber, LocalDate dueDate,
            InstallmentStatus status, BigDecimal initialBalance,
            BigDecimal interest, BigDecimal capitalAmortization,
            BigDecimal installmentAmount, BigDecimal finalBalance, BigDecimal paidAmount,
            BigDecimal lateFee,
            LocalDate actualPaymentDate) {

        if (installmentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Installment amount must be greater than 0");
        }

        this.installmentId = installmentId;
        this.loan = loan;
        this.installmentNumber = installmentNumber;
        this.dueDate = dueDate;
        this.status = status;
        this.initialBalance = initialBalance;
        this.interest = interest;
        this.capitalAmortization = capitalAmortization;
        this.installmentAmount = installmentAmount;
        this.finalBalance = finalBalance;
        this.paidAmount = paidAmount;
        this.lateFee = lateFee;
        this.actualPaymentDate = actualPaymentDate;
    }

    public Installment(
            Integer installmentNumber,
            LocalDate dueDate,
            InstallmentStatus status,
            BigDecimal initialBalance,
            BigDecimal interest,
            BigDecimal capitalAmortization,
            BigDecimal installmentAmount,
            BigDecimal finalBalance,
            BigDecimal paidAmount,
            BigDecimal lateFee,
            LocalDate actualPaymentDate) {

        this.installmentNumber = installmentNumber;
        this.dueDate = dueDate;
        this.status = status;
        this.initialBalance = initialBalance;
        this.interest = interest;
        this.capitalAmortization = capitalAmortization;
        this.installmentAmount = installmentAmount;
        this.finalBalance = finalBalance;
        this.paidAmount = paidAmount;
        this.lateFee = lateFee;
        this.actualPaymentDate = actualPaymentDate;
    }

    public static Installment create(
            Loan loan, int installmentNumber, BigDecimal installmentAmount,
            LocalDate dueDate, InstallmentStatus status,
            BigDecimal initialBalance, BigDecimal interest, BigDecimal capitalAmortization,
            BigDecimal finalBalance, BigDecimal paidAmount, BigDecimal lateFee,
            LocalDate actualPaymentDate) {

        return new Installment(
                null,
                loan,
                installmentNumber,
                dueDate,
                status,
                initialBalance,
                interest,
                capitalAmortization,
                installmentAmount,
                finalBalance,
                paidAmount,
                lateFee,
                actualPaymentDate);
    }
}