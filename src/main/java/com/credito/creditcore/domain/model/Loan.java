package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.model.enums.LoanType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Loan {

    private Integer loanId;
    private Customer customer;

    private BigDecimal principalAmount;
    private double interestRate;
    private int termInMonths;

    private LoanStatus loanStatus;
    private LocalDate applicationDate;
    private LocalDate approvalDate;
    private LoanType loanType;

    private BigDecimal totalInterest;
    private BigDecimal totalAmountDue;
    private BigDecimal totalPaid;
    private BigDecimal outstandingBalance;

    public Loan() {
    }

    public Loan(
            Integer loanId,
            Customer customer,
            BigDecimal principalAmount,
            double interestRate,
            int termInMonths,
            LoanStatus loanStatus,
            LocalDate applicationDate,
            LocalDate approvalDate,
            LoanType loanType,
            BigDecimal totalInterest,
            BigDecimal totalAmountDue,
            BigDecimal totalPaid,
            BigDecimal outstandingBalance) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer is required.");
        }

        if (termInMonths >= 24) {
            throw new IllegalArgumentException("Loan term cannot exceed two years.");
        }

        if (principalAmount.compareTo(BigDecimal.ZERO) <= 0
                && totalAmountDue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        this.loanId = loanId;
        this.customer = customer;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.loanStatus = loanStatus;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.loanType = loanType;
        this.totalInterest = totalInterest;
        this.totalAmountDue = totalAmountDue;
        this.totalPaid = totalPaid;
        this.outstandingBalance = outstandingBalance;
    }

    public Loan(
            Integer loanId,
            BigDecimal principalAmount,
            double interestRate,
            int termInMonths,
            LoanStatus loanStatus,
            LocalDate applicationDate,
            LocalDate approvalDate,
            LoanType loanType,
            BigDecimal totalInterest,
            BigDecimal totalAmountDue,
            BigDecimal totalPaid,
            BigDecimal outstandingBalance) {

        if (termInMonths >= 24) {
            throw new IllegalArgumentException("Loan term cannot exceed two years.");
        }

        if (principalAmount.compareTo(BigDecimal.ZERO) <= 0
                || totalAmountDue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Principal amount and total amount due must be greater than zero.");
        }

        this.loanId = loanId;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.loanStatus = loanStatus;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.loanType = loanType;
        this.totalInterest = totalInterest;
        this.totalAmountDue = totalAmountDue;
        this.totalPaid = totalPaid;
        this.outstandingBalance = outstandingBalance;
    }

    public static Loan create(
            Customer customer, BigDecimal principalAmount, double interestRate, int termInMonths,
            LoanStatus loanStatus, LocalDate applicationDate, LocalDate approvalDate, LoanType loanType,
            BigDecimal totalInterest, BigDecimal totalAmountDue, BigDecimal totalPaid,
            BigDecimal outstandingBalance) {

        return new Loan(
                null,
                customer,
                principalAmount,
                interestRate,
                termInMonths,
                loanStatus,
                applicationDate,
                approvalDate,
                loanType,
                totalInterest,
                totalAmountDue,
                totalPaid,
                outstandingBalance);
    }
}