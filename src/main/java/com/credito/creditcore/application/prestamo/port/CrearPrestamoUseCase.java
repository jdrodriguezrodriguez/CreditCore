package com.credito.creditcore.application.prestamo.port;

import com.credito.creditcore.application.dto.prestamo.CrearPrestamoRequestDto;
import com.credito.creditcore.domain.model.Loan;

public interface CrearPrestamoUseCase {
    Loan crearPrestamo(CrearPrestamoRequestDto datos, Integer idPersona);
}
