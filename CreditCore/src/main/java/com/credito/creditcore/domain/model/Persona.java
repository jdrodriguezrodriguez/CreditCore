package com.credito.creditcore.domain.model;

import java.time.LocalDate;

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

        if (nombre == null || apellido == null) {
            throw new IllegalArgumentException("el nombre y apellido son obligatorios.");
        }

        if (documento == null) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }

        if (documento.length() < 7 || documento.length() > 10) {
            throw new IllegalArgumentException("El documento es invalido.");
        }

        if (correo == null) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.nacimiento = nacimiento;
        this.correo = correo;
    }

    public static Persona crear(
            String nombre,
            String apellido,
            String documento,
            LocalDate nacimiento,
            String correo) {
        return new Persona(
                null,
                nombre,
                apellido,
                documento,
                nacimiento,
                correo);
    }

    /* public class Documento {

    private final String valor;

    public Documento(String valor) {
        if (valor == null) throw new IllegalArgumentException("Obligatorio");
        if (valor.length() < 7 || valor.length() > 10)
            throw new IllegalArgumentException("Inválido");

        this.valor = valor;
    }

    public String getValor() {
        return valor;
    } */

}

