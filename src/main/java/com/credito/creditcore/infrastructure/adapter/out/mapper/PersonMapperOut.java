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

        entity.setPersonId(person.getPersonId());

        return entity;
    }

    public static Person toDomain(PersonEntity entity) {
        return new Person(
                entity.getPersonId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDocumentNumber(),
                entity.getBirthDate(),
                entity.getEmail());
    }

    public static PersonEntity updateEntity(PersonEntity entity, Person person) {
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setBirthDate(person.getBirthDate());
        entity.setEmail(person.getEmail());

        return entity;
    }
}
