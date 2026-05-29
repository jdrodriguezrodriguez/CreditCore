package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;

public class PrestamoMapperOut {

    public static Prestamo toDomain(PrestamoEntity prestamoEntity) {
        return new Prestamo(
                prestamoEntity.getIdPrestamo(),
                prestamoEntity.getMonto(),
                prestamoEntity.getInteres(),
                prestamoEntity.getPlazo(),
                prestamoEntity.getEstadoPrestamo(),
                prestamoEntity.getFechaSolicitud(),
                prestamoEntity.getFechaAprobacion(),
                prestamoEntity.getTipoPrestamo(),
                prestamoEntity.getInteresTotal(),
                prestamoEntity.getTotalPagar(),
                prestamoEntity.getTotalPagado(),
                prestamoEntity.getSaldoPendiente());
    }

    public static PrestamoEntity crearEntidad(Prestamo prestamo, ClienteEntity clienteEntity) {
        return new PrestamoEntity(
                clienteEntity,
                prestamo.getMonto(),
                prestamo.getInteres(),
                prestamo.getPlazo(),
                prestamo.getEstadoPrestamo(),
                prestamo.getFechaSolicitud(),
                prestamo.getFechaAprobacion(),
                prestamo.getTipoPrestamo(),
                prestamo.getInteresTotal(),
                prestamo.getTotalPagar(),
                prestamo.getTotalPagado(),
                prestamo.getSaldoPendiente());
    }

    public static PrestamoEntity toEntity(Prestamo prestamo, ClienteEntity clienteEntity) {
        PrestamoEntity prestamoEntity = new PrestamoEntity(
                clienteEntity,
                prestamo.getMonto(),
                prestamo.getInteres(),
                prestamo.getPlazo(),
                prestamo.getEstadoPrestamo(),
                prestamo.getFechaSolicitud(),
                prestamo.getFechaAprobacion(),
                prestamo.getTipoPrestamo(),
                prestamo.getInteresTotal(),
                prestamo.getTotalPagar(),
                prestamo.getTotalPagado(),
                prestamo.getSaldoPendiente());

        prestamoEntity.setIdPrestamo(prestamo.getIdPrestamo());

        return prestamoEntity;
    }

    public static PrestamoEntity actualizarEntidad(PrestamoEntity prestamoEntity, Prestamo prestamo) {
        prestamoEntity.setFechaAprobacion(prestamo.getFechaAprobacion());
        prestamoEntity.setEstadoPrestamo(prestamo.getEstadoPrestamo());

        return prestamoEntity;
    }
}
