package com.credito.creditcore.domain.model;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.PaymentConcept;
import com.credito.creditcore.domain.model.enums.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {

    private Integer paymentId;
    private Installment installment;
    private BigDecimal paidAmount;
    private PaymentMethod paymentMethod;
    private PaymentConcept paymentConcept;

    public Payment() {
    }

    public Payment(
            Installment installment, BigDecimal paidAmount,
            PaymentMethod paymentMethod, PaymentConcept paymentConcept) {

        if (paidAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than $0.");
        }

        this.installment = installment;
        this.paidAmount = paidAmount;
        this.paymentMethod = paymentMethod;
        this.paymentConcept = paymentConcept;
    }

    public Payment(
            BigDecimal paidAmount,
            PaymentMethod paymentMethod, PaymentConcept paymentConcept) {

        if (paidAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than $0.");
        }

        this.paidAmount = paidAmount;
        this.paymentMethod = paymentMethod;
        this.paymentConcept = paymentConcept;
    }

    public static Payment create(
            Installment installment, BigDecimal paidAmount,
            PaymentMethod paymentMethod, PaymentConcept paymentConcept) {

        return new Payment(
                installment,
                paidAmount,
                paymentMethod,
                paymentConcept);
    }
}