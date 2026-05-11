package com.credito.creditcore.application.prestamo.port;

import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoRequestDto;
import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoResponseDto;

public interface SimularPrestamoUseCase {
    SimuladorPrestamoResponseDto simularPrestamo(SimuladorPrestamoRequestDto datos, Integer idPersona);
}
