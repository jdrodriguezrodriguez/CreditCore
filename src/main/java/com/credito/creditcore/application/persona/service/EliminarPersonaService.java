package com.credito.creditcore.application.persona.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.persona.port.in.EliminarPersonaUseCase;
import com.credito.creditcore.domain.port.PersonaRepositoryPort;

@Service
public class EliminarPersonaService implements EliminarPersonaUseCase{

    private PersonaRepositoryPort personaRepositoryPort;

    public EliminarPersonaService(PersonaRepositoryPort personaRepositoryPort) {
        this.personaRepositoryPort = personaRepositoryPort;
    }

    @Override
    public void eliminarPersona(String documento) {

        if (documento == null) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }

        if (documento.length() < 7 || documento.length() > 10) {
            throw new IllegalArgumentException("El documento es invalido.");
        }

        personaRepositoryPort.eliminar(documento);
    }
    
}
