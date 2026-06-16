package com.credito.creditcore.application.persona.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.persona.port.in.ObtenerPersonaUseCase;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.PersonRepositoryPort;

@Service
public class ObtenerPersonaService implements ObtenerPersonaUseCase {

    private final PersonRepositoryPort personaRepositoryPort;

    public ObtenerPersonaService(PersonRepositoryPort personaRepositoryPort) {
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public Person obtenerPersona(String documento) {

        if (documento == null) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }

        if (documento.length() < 7 || documento.length() > 10) {
            throw new IllegalArgumentException("El documento es invalido.");
        }

        Person persona = personaRepositoryPort.consultar(documento)
            .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ese documento."));

        if (persona == null) {
            throw new RuntimeException("Persona no encontrada");
        }

        return persona;
    }
}
