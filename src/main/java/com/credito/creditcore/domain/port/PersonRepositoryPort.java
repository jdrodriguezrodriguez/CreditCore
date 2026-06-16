package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Person;

public interface PersonRepositoryPort {
    Person save(Person persona);
    Optional<Person> findByDocumentNumber(String documento);
    void update(String documento, Person persona);
    void delete(String documento);
    boolean existsByDocumentNumber(String documento);

    Optional<Person> findById(Integer idPersona);
} 