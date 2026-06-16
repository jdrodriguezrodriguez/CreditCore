package com.credito.creditcore.application.persona.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.persona.port.in.ActualizarPersonaUseCase;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.PersonRepositoryPort;

@Service
public class ActualizarPersonaService implements ActualizarPersonaUseCase{

    private final PersonRepositoryPort personaRepositoryPort;

    public ActualizarPersonaService(PersonRepositoryPort personaRepositoryPort){
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public void actualizarPersona(String documento, Person persona) {

        if (documento == null) {
            throw new IllegalArgumentException("El documento es obligatorio.");
        }

        if (documento.length() < 7 || documento.length() > 10) {
            throw new IllegalArgumentException("El documento es invalido.");
        }

        if (!documento.equals(persona.getDocumento())) {
            throw new IllegalArgumentException("El documento no se puede actualizar.");
        }

        personaRepositoryPort.actualizar(documento, persona);
    }
}
