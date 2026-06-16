package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public class PersonaMapperOut {

    public static PersonaEntity crearEntidad(Person persona) {
        return new PersonaEntity(
                persona.getNombre(),
                persona.getApellido(),
                persona.getDocumento(),
                persona.getNacimiento(),
                persona.getCorreo());
    }

    public static PersonaEntity toEntity(Person persona) {
        PersonaEntity entity = new PersonaEntity(
                persona.getNombre(),
                persona.getApellido(),
                persona.getDocumento(),
                persona.getNacimiento(),
                persona.getCorreo());

        entity.setIdPersona(persona.getIdPersona());

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
        pEntity.setNombre(persona.getNombre());
        pEntity.setApellido(persona.getApellido());
        pEntity.setNacimiento(persona.getNacimiento());
        pEntity.setCorreo(persona.getCorreo());

        return pEntity;
    }
}
