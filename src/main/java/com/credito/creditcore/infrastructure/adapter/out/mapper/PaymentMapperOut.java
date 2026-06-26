package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.PaymentMethod;
import com.credito.creditcore.infrastructure.entity.InstallmentEntity;
import com.credito.creditcore.infrastructure.entity.PaymentEntity;

public class PaymentMapperOut {

    public static PaymentEntity createEntity(
            Integer installmentId, PaymentMethod paymentMethod, BigDecimal amount, InstallmentEntity installmentEntity) {

        return new PaymentEntity(
                installmentEntity,
                amount,
                LocalDate.now(),
                paymentMethod);
    }
}