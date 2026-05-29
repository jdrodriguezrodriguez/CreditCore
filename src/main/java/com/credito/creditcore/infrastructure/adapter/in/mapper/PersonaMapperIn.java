package com.credito.creditcore.infrastructure.adapter.in.mapper;

import java.time.LocalDate;

import com.credito.creditcore.application.dto.persona.PersonaDto;
import com.credito.creditcore.domain.model.Persona;

public class PersonaMapperIn {

    public static Persona crearModelo(PersonaDto datos) {
        return Persona.crear(
            datos.nombre(), 
            datos.apellido(), 
            datos.documento(), 
            LocalDate.parse(datos.nacimiento()), 
            datos.correo());
    }

    public static PersonaDto crearDto(Persona persona){
       return new PersonaDto(
            persona.getNombre(), 
            persona.getApellido(), 
            persona.getDocumento(),
            persona.getNacimiento().toString(), 
            persona.getCorreo()
        );
    }
    
}
