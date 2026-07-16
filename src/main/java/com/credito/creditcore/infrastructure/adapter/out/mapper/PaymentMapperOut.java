package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.time.LocalDate;

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
}