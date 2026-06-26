package com.credito.creditcore.infrastructure.adapter.out;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.enums.PaymentMethod;
import com.credito.creditcore.domain.port.PaymentRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.InstallmentMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PaymentMapperOut;
import com.credito.creditcore.infrastructure.persistence.PayRepositoryJpa;


@Component
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final PayRepositoryJpa repositoryJpa;

    public PaymentRepositoryAdapter(PayRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public void savePayment(Installment installment, PaymentMethod paymentMethod, BigDecimal amount) {
        repositoryJpa.save(PaymentMapperOut.createEntity(
                installment.getInstallmentId(),
                paymentMethod,
                amount,
                InstallmentMapperOut.toEntity(installment)));
    }
}
