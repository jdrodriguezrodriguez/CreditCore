package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.PaymentConcept;
import com.credito.creditcore.domain.model.enums.PaymentMethod;

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

@Getter
@Setter
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "installment_id", referencedColumnName = "installment_id")
    private InstallmentEntity installmentEntity;

    @Column(name = "paid_amount", nullable = false)
    private BigDecimal paidAmount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_concept", nullable = false)
    private PaymentConcept paymentConcept;

    public PaymentEntity(){}

    public PaymentEntity(InstallmentEntity installmentEntity, BigDecimal paidAmount, LocalDate paymentDate,
            PaymentMethod paymentMethod, PaymentConcept paymentConcept) {
        this.installmentEntity = installmentEntity;
        this.paidAmount = paidAmount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentConcept = paymentConcept;
    }
}
