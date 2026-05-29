package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.Fpago;
import com.credito.creditcore.infrastructure.entity.CuotaEntity;
import com.credito.creditcore.infrastructure.entity.PagoEntity;

public class PagoMapperOut {
    public static PagoEntity crearEntity(Integer cuotaId, Fpago fpago, BigDecimal monto, CuotaEntity cuotaEntity){
        return new PagoEntity(
            cuotaEntity, 
            monto, LocalDate.now(), fpago);
    }
}
