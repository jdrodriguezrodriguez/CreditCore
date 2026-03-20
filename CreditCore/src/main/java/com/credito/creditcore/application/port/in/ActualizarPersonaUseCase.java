package com.credito.creditcore.application.port.in;

import com.credito.creditcore.domain.model.Persona;

public interface ActualizarPersonaUseCase {
    void actualizarPersona(String documento, Persona persona);
}
