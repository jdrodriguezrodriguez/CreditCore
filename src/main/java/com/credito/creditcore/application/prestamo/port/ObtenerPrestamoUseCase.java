package com.credito.creditcore.application.prestamo.port;

import com.credito.creditcore.domain.model.Loan;

public interface ObtenerPrestamoUseCase {
    Loan obtenerPrestamo(Integer idPersona);
} 