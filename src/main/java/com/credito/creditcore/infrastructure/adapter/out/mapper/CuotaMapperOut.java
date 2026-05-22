package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.util.ArrayList;
import java.util.List;

import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.infrastructure.entity.CuotaEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;

public class CuotaMapperOut {
    public static List<CuotaEntity> crearEntidad(List<Cuota> cuotas, PrestamoEntity prestamoEntity) {
        List<CuotaEntity> cuotaEntities = new ArrayList<>();

        for (Cuota cuota : cuotas) {
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
}
