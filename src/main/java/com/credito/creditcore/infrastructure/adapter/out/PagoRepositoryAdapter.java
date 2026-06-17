package com.credito.creditcore.infrastructure.adapter.out;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.enums.PaymentMethod;
import com.credito.creditcore.domain.port.PaymentRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.CuotaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PagoMapperOut;
import com.credito.creditcore.infrastructure.persistence.PagoRepositoryJpa;


@Component
public class PagoRepositoryAdapter implements PaymentRepositoryPort {

    private final PagoRepositoryJpa repositoryJpa;

    public PagoRepositoryAdapter(PagoRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public void savePayment(Installment cuota, PaymentMethod fpago, BigDecimal monto) {
        repositoryJpa.save(PagoMapperOut.crearEntity(
                cuota.getIdCuota(),
                fpago,
                monto,
                CuotaMapperOut.toEntity(cuota)));
    }
}
