package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.port.PersonRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonMapperOut;
import com.credito.creditcore.infrastructure.entity.PersonEntity;
import com.credito.creditcore.infrastructure.persistence.PersonRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class PersonRepositoryAdapter implements PersonRepositoryPort {

    private final PersonRepositoryJpa repositoryJpa;

    public PersonRepositoryAdapter(PersonRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Person save(Person person) {

        PersonEntity personEntity = PersonMapperOut.crearEntidad(person);

        PersonEntity saved = repositoryJpa.save(personEntity);

        return PersonMapperOut.toDomain(saved);
    }

    @Override
    public Optional<Person> findByDocumentNumber(String documentNumber) {
        return repositoryJpa.findByDocumento(documentNumber)
                .map(PersonMapperOut::toDomain);
    }

    @Override
    public void update(String documentNumber, Person person) {
        PersonEntity pEntity = repositoryJpa.findByDocumento(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException());

        repositoryJpa.save(PersonMapperOut.updateEntity(pEntity, person));
    }

    @Override
    public void delete(String documentNumber) {
        PersonEntity pEntity = repositoryJpa.findByDocumento(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException());

        repositoryJpa.delete(pEntity);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return repositoryJpa.existsByDocumento(documentNumber);
    }

    @Override
    public Optional<Person> findById(Integer personId) {
        return repositoryJpa.findById(personId).map(
                PersonMapperOut::toDomain);
    }
}
