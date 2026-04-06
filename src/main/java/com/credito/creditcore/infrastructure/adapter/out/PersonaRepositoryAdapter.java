package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.domain.port.PersonaRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.persistence.PersonaRepositoryJpa;

@Component
public class PersonaRepositoryAdapter implements PersonaRepositoryPort {

    private final PersonaRepositoryJpa repositoryJpa;

    public PersonaRepositoryAdapter(PersonaRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Persona guardar(Persona persona) {

        PersonaEntity personaEntity = PersonaMapperOut.crearEntidad(persona);

        PersonaEntity saved = repositoryJpa.save(personaEntity);

        return PersonaMapperOut.toDomain(saved);
    }

    @Override
    public Optional<Persona> consultar(String documento) {
        return repositoryJpa.findByDocumento(documento)
                .map(PersonaMapperOut::toDomain);
    }

    @Override
    public void actualizar(String documento, Persona persona) {
        PersonaEntity pEntity = repositoryJpa.findByDocumento(documento)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ese documento."));

        repositoryJpa.save(PersonaMapperOut.updateEntity(pEntity, persona));
    }

    @Override
    public void eliminar(String documento) {
        PersonaEntity pEntity = repositoryJpa.findByDocumento(documento)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ese documento."));

        repositoryJpa.delete(pEntity);
    }

    @Override
    public boolean existePorDocumento(String documento) {
        return repositoryJpa.existsByDocumento(documento);
    }

    @Override
    public Optional<Persona> findById(Integer idPersona) {
        return repositoryJpa.findById(idPersona).map(
                PersonaMapperOut::toDomain);
    }
}
