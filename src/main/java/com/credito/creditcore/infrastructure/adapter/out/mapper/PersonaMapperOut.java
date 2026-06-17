package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public class PersonaMapperOut {

    public static PersonaEntity crearEntidad(Person persona) {
        return new PersonaEntity(
                persona.getFirstName(),
                persona.getLastName(),
                persona.getDocumentNumber(),
                persona.getBirthDate(),
                persona.getEmail());
    }

    public static PersonaEntity toEntity(Person persona) {
        PersonaEntity entity = new PersonaEntity(
                persona.getFirstName(),
                persona.getLastName(),
                persona.getDocumentNumber(),
                persona.getBirthDate(),
                persona.getEmail());

        entity.setIdPersona(persona.getPersonId());

        return entity;
    }

    public static Person toDomain(PersonaEntity pEntity) {
        return new Person(
                pEntity.getIdPersona(),
                pEntity.getNombre(),
                pEntity.getApellido(),
                pEntity.getDocumento(),
                pEntity.getNacimiento(),
                pEntity.getCorreo());
    }

    public static PersonaEntity updateEntity(PersonaEntity pEntity, Person persona) {
        pEntity.setNombre(persona.getFirstName());
        pEntity.setApellido(persona.getLastName());
        pEntity.setNacimiento(persona.getBirthDate());
        pEntity.setCorreo(persona.getEmail());

        return pEntity;
    }
}
