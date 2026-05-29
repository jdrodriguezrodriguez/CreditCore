package com.credito.creditcore.application.prestamo.port;

import com.credito.creditcore.application.dto.prestamo.CrearPrestamoRequestDto;
import com.credito.creditcore.domain.model.Prestamo;

public interface CrearPrestamoUseCase {
    Prestamo crearPrestamo(CrearPrestamoRequestDto datos, Integer idPersona);
}
