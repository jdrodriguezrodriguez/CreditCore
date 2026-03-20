package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public class PersonaMapperOut {

    public static PersonaEntity crearEntidad(Persona persona) {
        return new PersonaEntity(
                persona.getNombre(),
                persona.getApellido(),
                persona.getDocumento(),
                persona.getNacimiento(),
                persona.getCorreo());
    }

    public static Persona toDomain(PersonaEntity pEntity) {
        return new Persona(
                pEntity.getIdPersona(),
                pEntity.getNombre(),
                pEntity.getApellido(),
                pEntity.getDocumento(),
                pEntity.getNacimiento(),
                pEntity.getCorreo());
    }

    public static PersonaEntity updateEntity(PersonaEntity pEntity, Persona persona) {
        pEntity.setNombre(persona.getNombre());
        pEntity.setApellido(persona.getApellido());
        pEntity.setNacimiento(persona.getNacimiento());
        pEntity.setCorreo(persona.getCorreo());

        return pEntity;
    }
}
