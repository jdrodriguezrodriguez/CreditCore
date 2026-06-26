package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.model.enums.LoanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "loan")
@Getter
@Setter
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer loanId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "principal_amount", nullable = false)
    private BigDecimal principalAmount;

    @Column(name = "interest_rate", nullable = false)
    private double interestRate;

    @Column(name = "term_in_months", nullable = false)
    private int termInMonths;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status", nullable = false)
    private LoanStatus loanStatus;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LoanType loanType;

    @Column(name = "total_interest", nullable = false)
    private BigDecimal totalInterest;

    @Column(name = "total_amount_payable", nullable = false)
    private BigDecimal totalAmountDue;

    @Column(name = "amount_paid", nullable = false)
    private BigDecimal totalPaid;

    @Column(name = "outstanding_balance", nullable = false)
    private BigDecimal outstandingBalance;

    public LoanEntity() {
    }

    public LoanEntity(CustomerEntity customer, BigDecimal principalAmount, double interestRate,
            Integer termInMonths, LoanStatus loanStatus, LocalDate requestDate, LocalDate approvalDate,
            LoanType loanType, BigDecimal totalInterest, BigDecimal totalAmountDue, BigDecimal totalPaid,
            BigDecimal outstandingBalance) {
        this.customer = customer;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.loanStatus = loanStatus;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.loanType = loanType;
        this.totalInterest = totalInterest;
        this.totalAmountDue = totalAmountDue;
        this.totalPaid = totalPaid;
        this.outstandingBalance = outstandingBalance;
    }
}
