package com.credito.creditcore.application.prestamo.port;

import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoDto;
import com.credito.creditcore.domain.model.Prestamo;

public interface SimularPrestamoUseCase {
    Prestamo simulacroPrestamo(SimuladorPrestamoDto datos, Integer idPersona);
}
