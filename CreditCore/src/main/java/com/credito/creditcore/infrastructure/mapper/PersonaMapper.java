package com.credito.creditcore.infrastructure.mapper;

import com.credito.creditcore.domain.model.Persona;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public class PersonaMapper {
    
    public static PersonaEntity crearEntidad(Persona persona){
        return new PersonaEntity(
                persona.getNombre(),
                persona.getApellido(),
                persona.getDocumento(),
                persona.getNacimiento(),
                persona.getCorreo());
    }
}
