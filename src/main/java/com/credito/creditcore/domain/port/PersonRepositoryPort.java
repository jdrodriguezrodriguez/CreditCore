package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Person;

public interface PersonRepositoryPort {
    Person guardar(Person persona);
    Optional<Person> consultar(String documento);
    void actualizar(String documento, Person persona);
    void eliminar(String documento);
    boolean existePorDocumento(String documento);

    Optional<Person> findById(Integer idPersona);
} 