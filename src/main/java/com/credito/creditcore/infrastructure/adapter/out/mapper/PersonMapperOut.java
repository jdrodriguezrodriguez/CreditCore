package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.entity.PersonEntity;

public class PersonMapperOut {

    public static PersonEntity crearEntidad(Person person) {
        return new PersonEntity(
                person.getFirstName(),
                person.getLastName(),
                person.getDocumentNumber(),
                person.getBirthDate(),
                person.getEmail());
    }

    public static PersonEntity toEntity(Person person) {
        PersonEntity entity = new PersonEntity(
                person.getFirstName(),
                person.getLastName(),
                person.getDocumentNumber(),
                person.getBirthDate(),
                person.getEmail());

        entity.setIdPersona(person.getPersonId());

        return entity;
    }

    public static Person toDomain(PersonEntity entity) {
        return new Person(
                entity.getIdPersona(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getDocumento(),
                entity.getNacimiento(),
                entity.getCorreo());
    }

    public static PersonEntity updateEntity(PersonEntity entity, Person person) {
        entity.setNombre(person.getFirstName());
        entity.setApellido(person.getLastName());
        entity.setNacimiento(person.getBirthDate());
        entity.setCorreo(person.getEmail());

        return entity;
    }
}
