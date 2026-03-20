package com.credito.creditcore.application.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.port.in.ObtenerPersonaUseCase;
import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.port.PersonaRepositoryPort;

@Service
public class ObtenerPersonaService implements ObtenerPersonaUseCase {

    private PersonaRepositoryPort personaRepositoryPort;

    public ObtenerPersonaService(PersonaRepositoryPort personaRepositoryPort) {
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public Persona obtenerPersona(String documento) {

        if (documento == null) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }

        if (documento.length() < 7 || documento.length() > 10) {
            throw new IllegalArgumentException("El documento es invalido.");
        }

        Persona persona = personaRepositoryPort.consultar(documento);

        if (persona == null) {
            throw new RuntimeException("Persona no encontrada");
        }

        return persona;
    }
}
