package com.credito.creditcore.application.persona.port.in;

import com.credito.creditcore.domain.model.Person;

public interface ActualizarPersonaUseCase {
    void actualizarPersona(String documento, Person persona);
}
