package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Person;

public interface PersonRepositoryPort {
    Person save(Person persona);
    Optional<Person> findByDocumentNumber(String documentNumber);
    void update(String documentNumber, Person person);
    void delete(String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);

    Optional<Person> findById(Integer personId);
} 