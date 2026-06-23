package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.infrastructure.entity.CuotaEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;

public class CuotaMapperOut {
    public static List<CuotaEntity> toEntityList(List<Installment> installments, PrestamoEntity prestamoEntity) {
        List<CuotaEntity> table = new ArrayList<>();

        for (Installment installment : installments) {
            CuotaEntity installmEntity = new CuotaEntity(
                    prestamoEntity,
                    installment.getInstallmentNumber(),
                    installment.getInstallmentAmount(),
                    installment.getDueDate(),
                    installment.getStatus(),
                    installment.getInitialBalance(),
                    installment.getInterest(),
                    installment.getCapitalAmortization(),
                    installment.getFinalBalance(),
                    installment.getPaidAmount(),
                    installment.getLateFee(),
                    installment.getActualPaymentDate());

            table.add(installmEntity);
        }

        return table;
    }

    public static List<Installment> toDomainList(List<CuotaEntity> cuotaEntities) {
        List<Installment> table = new ArrayList<>();

        for (CuotaEntity cuota : cuotaEntities) {
            Installment installment = new Installment(
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
                    cuota.getFechaPagoReal());

            table.add(installment);
        }

        return table;
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

    public static CuotaEntity toEntity(Installment installment) {
        CuotaEntity cuotasEntity = new CuotaEntity(
                null,
                installment.getInstallmentNumber(),
                installment.getInstallmentAmount(),
                installment.getDueDate(),
                installment.getStatus(),
                installment.getInitialBalance(),
                installment.getInterest(),
                installment.getCapitalAmortization(),
                installment.getFinalBalance(),
                installment.getPaidAmount(),
                installment.getLateFee(),
                installment.getActualPaymentDate());

        cuotasEntity.setIdCuota(installment.getInstallmentId());

        return cuotasEntity;
    }
}
