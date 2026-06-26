package com.credito.creditcore.domain.model;

import java.math.BigDecimal;

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

    public Payment() {
    }

    public Payment(
            Installment installment,
            BigDecimal paidAmount,
            PaymentMethod paymentMethod) {

        if (paidAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than $0.");
        }

        this.installment = installment;
        this.paidAmount = paidAmount;
        this.paymentMethod = paymentMethod;
    }

    public static Payment create(
            Installment installment,
            BigDecimal paidAmount,
            PaymentMethod paymentMethod) {

        return new Payment(
                installment,
                paidAmount,
                paymentMethod);
    }
}