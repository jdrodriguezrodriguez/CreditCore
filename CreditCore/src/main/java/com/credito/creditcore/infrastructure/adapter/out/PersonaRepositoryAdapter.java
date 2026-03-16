package com.credito.creditcore.infrastructure.adapter.out;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.port.PersonaRepositoryPort;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.mapper.PersonaMapper;
import com.credito.creditcore.infrastructure.persistence.PersonaRepositoryJpa;

@Component
public class PersonaRepositoryAdapter implements PersonaRepositoryPort {

    private final PersonaRepositoryJpa repositoryJpa;

    public PersonaRepositoryAdapter(PersonaRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Persona guardar(Persona persona) {

        PersonaEntity personaEntity = PersonaMapper.crearEntidad(persona);

        PersonaEntity saved = repositoryJpa.save(personaEntity);

        return new Persona(
                saved.getIdPersona(),
                saved.getNombre(),
                saved.getApellido(),
                saved.getDocumento(),
                saved.getNacimiento(),
                saved.getCorreo());
    }
}
