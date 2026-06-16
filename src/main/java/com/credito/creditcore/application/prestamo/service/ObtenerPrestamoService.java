package com.credito.creditcore.application.prestamo.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.prestamo.port.ObtenerPrestamoUseCase;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.port.LoanRepositoryPort;

@Service
public class ObtenerPrestamoService implements ObtenerPrestamoUseCase {

    private final LoanRepositoryPort prestamorepositoryPort;

    public ObtenerPrestamoService(LoanRepositoryPort prestamorepositoryPort) {
        this.prestamorepositoryPort = prestamorepositoryPort;
    }

    @Override
    public Prestamo obtenerPrestamo(Integer idCliente) {

        Prestamo prestamo = prestamorepositoryPort.obtenerPorIdCliente(idCliente)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No se encontro el prestamo con el idPersona: " + idCliente));

        return prestamo;
    }
}
