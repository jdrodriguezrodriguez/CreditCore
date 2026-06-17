package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.infrastructure.entity.CuotaEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;

public class CuotaMapperOut {
    public static List<CuotaEntity> toEntityList(List<Installment> cuotas, PrestamoEntity prestamoEntity) {
        List<CuotaEntity> cuotaEntities = new ArrayList<>();

        for (Installment cuota : cuotas) {
            CuotaEntity cuotasEntity = new CuotaEntity(
                    prestamoEntity,
                    cuota.getNumero_cuota(),
                    cuota.getMontoCuota(),
                    cuota.getFecha_vencimiento(),
                    cuota.getEstadoCuota(),
                    cuota.getSaldoInicial(),
                    cuota.getInteres(),
                    cuota.getAmortizacionCapital(),
                    cuota.getSaldoFinal(),
                    cuota.getMontoPagado(),
                    cuota.getMora(),
                    cuota.getFechaPagoReal());

            cuotaEntities.add(cuotasEntity);
        }

        return cuotaEntities;
    }

    public static List<Installment> toDomainList(List<CuotaEntity> cuotaEntities){
        List<Installment> cuotasModelos = new ArrayList<>();

        for (CuotaEntity cuota : cuotaEntities) {
            Installment cuotaModel = new Installment(
                    cuota.getNumeroCuota(),
                    cuota.getFechaVencimiento(),
                    cuota.getEstadoCuota(),
                    cuota.getSaldoInicial(),
                    cuota.getInteres(),
                    cuota.getAmortizacionCapital(),
                    cuota.getMontoCuota(),
                    cuota.getSaldoFinal(),
                    cuota.getMontoPagado(),
                    cuota.getMora(),
                    cuota.getFechaPagoReal()
                    );

            cuotasModelos.add(cuotaModel);
        }

        return cuotasModelos;
    }

    public static Installment toDomain(CuotaEntity cuotaEntity) {
        return new Installment(
                cuotaEntity.getIdCuota(),
                null,
                cuotaEntity.getNumeroCuota(),
                cuotaEntity.getFechaVencimiento(),
                cuotaEntity.getEstadoCuota(),
                cuotaEntity.getSaldoInicial(),
                cuotaEntity.getInteres(),
                cuotaEntity.getAmortizacionCapital(),
                cuotaEntity.getMontoCuota(),
                cuotaEntity.getSaldoFinal(),
                cuotaEntity.getMontoPagado(),
                cuotaEntity.getMora(),
                cuotaEntity.getFechaPagoReal());
    }

    public static CuotaEntity updateEntity(CuotaEntity cuotaEntity, BigDecimal monto) {
        cuotaEntity.setMontoPagado(monto);

        return cuotaEntity;
    }

    public static CuotaEntity toEntity(Installment cuota) {
        CuotaEntity cuotasEntity = new CuotaEntity(
                null,
                cuota.getNumero_cuota(),
                cuota.getMontoCuota(),
                cuota.getFecha_vencimiento(),
                cuota.getEstadoCuota(),
                cuota.getSaldoInicial(),
                cuota.getInteres(),
                cuota.getAmortizacionCapital(),
                cuota.getSaldoFinal(),
                cuota.getMontoPagado(),
                cuota.getMora(),
                cuota.getFechaPagoReal());

        cuotasEntity.setIdCuota(cuota.getIdCuota());

        return cuotasEntity;
    }
}
