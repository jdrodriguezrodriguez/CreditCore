package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.InstallmentStatus;

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
@Table(name = "installment")
@Getter
@Setter
public class InstallmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "installment_id")
    private Integer installmentId;

    @ManyToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "loan_id")
    private LoanEntity loan;

    @Column(name = "installment_number", nullable = false)
    private Integer installmentNumber;

    @Column(name = "installment_amount", nullable = false)
    private BigDecimal installmentAmount;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "installment_status", nullable = false)
    private InstallmentStatus status;

    @Column(name = "initial_balance", nullable = false)
    private BigDecimal initialBalance;

    @Column(name = "interest", nullable = false)
    private BigDecimal interest;

    @Column(name = "capital_amortization", nullable = false)
    private BigDecimal capitalAmortization;

    @Column(name = "final_balance", nullable = false)
    private BigDecimal finalBalance;

    @Column(name = "paid_amount", nullable = false)
    private BigDecimal paidAmount;

    @Column(name = "late_fee", nullable = false)
    private BigDecimal lateFee;

    @Column(name = "actual_payment_date")
    private LocalDate actualPaymentDate;

    public InstallmentEntity() {
    }

    public InstallmentEntity(LoanEntity loan, Integer installmentNumber, BigDecimal installmentAmount,
            LocalDate dueDate, InstallmentStatus status, BigDecimal initialBalance, BigDecimal interest,
            BigDecimal capitalAmortization, BigDecimal finalBalance, BigDecimal paidAmount, BigDecimal lateFee,
            LocalDate actualPaymentDate) {
        this.loan = loan;
        this.installmentNumber = installmentNumber;
        this.installmentAmount = installmentAmount;
        this.dueDate = dueDate;
        this.status = status;
        this.initialBalance = initialBalance;
        this.interest = interest;
        this.capitalAmortization = capitalAmortization;
        this.finalBalance = finalBalance;
        this.paidAmount = paidAmount;
        this.lateFee = lateFee;
        this.actualPaymentDate = actualPaymentDate;
    }
}
