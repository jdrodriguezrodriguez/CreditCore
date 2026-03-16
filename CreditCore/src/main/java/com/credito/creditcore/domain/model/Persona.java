package com.credito.creditcore.domain.model;

import java.time.LocalDate;

import com.credito.creditcore.application.dto.PersonaDto;

import lombok.Getter;

@Getter
public class Persona {
    
    private Integer idPersona;

    private String nombre;
    private String apellido;
    private String documento;
    private LocalDate nacimiento;
    private String correo;

    public Persona(Integer idPersona, String nombre, String apellido, String documento,
            LocalDate nacimiento, String correo) {

        if (documento == null) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.nacimiento = nacimiento;
        this.correo = correo;
    }

    public static Persona crear(PersonaDto datos){
        return new Persona(
            null, 
            datos.nombre(), 
            datos.apellido(), 
            datos.documento(), 
            datos.nacimiento(), 
            datos.correo());
    }
}
