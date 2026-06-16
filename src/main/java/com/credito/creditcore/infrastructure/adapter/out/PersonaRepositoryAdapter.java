package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.PersonRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.persistence.PersonaRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class PersonaRepositoryAdapter implements PersonRepositoryPort {

    private final PersonaRepositoryJpa repositoryJpa;

    public PersonaRepositoryAdapter(PersonaRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Person save(Person persona) {

        PersonaEntity personaEntity = PersonaMapperOut.crearEntidad(persona);

        PersonaEntity saved = repositoryJpa.save(personaEntity);

        return PersonaMapperOut.toDomain(saved);
    }

    @Override
    public Optional<Person> findByDocumentNumber(String documento) {
        return repositoryJpa.findByDocumento(documento)
                .map(PersonaMapperOut::toDomain);
    }

    @Override
    public void update(String documento, Person persona) {
        PersonaEntity pEntity = repositoryJpa.findByDocumento(documento)
                .orElseThrow(() -> new EntityNotFoundException());

        repositoryJpa.save(PersonaMapperOut.updateEntity(pEntity, persona));
    }

    @Override
    public void delete(String documento) {
        PersonaEntity pEntity = repositoryJpa.findByDocumento(documento)
                .orElseThrow(() -> new EntityNotFoundException());

        repositoryJpa.delete(pEntity);
    }

    @Override
    public boolean existsByDocumentNumber(String documento) {
        return repositoryJpa.existsByDocumento(documento);
    }

    @Override
    public Optional<Person> findById(Integer idPersona) {
        return repositoryJpa.findById(idPersona).map(
                PersonaMapperOut::toDomain);
    }
}
