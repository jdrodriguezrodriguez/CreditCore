package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.credito.creditcore.domain.model.Payment;
import com.credito.creditcore.infrastructure.entity.InstallmentEntity;
import com.credito.creditcore.infrastructure.entity.PaymentEntity;

public class PaymentMapperOut {

    public static PaymentEntity createEntity(
            Payment payment, InstallmentEntity installmentEntity) {

        return new PaymentEntity(
                installmentEntity,
                payment.getPaidAmount(),
                LocalDate.now(),
                payment.getPaymentMethod(),
                payment.getPaymentConcept());
    }

    public static List<Payment> toDomainList(List<PaymentEntity> PaymentEntities) {
        List<Payment> payments = new ArrayList<>();

        for (PaymentEntity paymentEntity : PaymentEntities) {
            Payment payment = new Payment(
                    paymentEntity.getPaidAmount(),
                    paymentEntity.getPaymentMethod(),
                    paymentEntity.getPaymentConcept());

            payments.add(payment);
        }

        return payments;
    }
}