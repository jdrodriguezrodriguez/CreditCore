package com.credito.creditcore.application.prestamo.port;

import com.credito.creditcore.domain.model.Prestamo;

public interface ObtenerPrestamoUseCase {
    Prestamo obtenerPrestamo(Integer idPersona);
} 