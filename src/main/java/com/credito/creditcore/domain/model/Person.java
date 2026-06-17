package com.credito.creditcore.domain.model;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Person {

    private Integer personId;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private LocalDate birthDate;
    private String email;

    public Person() {
    }

    public Person(
            Integer personId,
            String firstName,
            String lastName,
            String documentNumber,
            LocalDate birthDate,
            String email) {

        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First name and last name are required.");
        }

        if (documentNumber == null) {
            throw new IllegalArgumentException("Document number is required.");
        }

        if (documentNumber.length() < 7 || documentNumber.length() > 10) {
            throw new IllegalArgumentException("Document number is invalid.");
        }

        if (email == null) {
            throw new IllegalArgumentException("Email is required.");
        }

        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.email = email;
    }

    public static Person create(
            String firstName, String lastName, String documentNumber,
            LocalDate birthDate, String email) {

        return new Person(
                null,
                firstName,
                lastName,
                documentNumber,
                birthDate,
                email);
    }
}

/*
 * public class Documento {
 * 
 * private final String valor;
 * 
 * public Documento(String valor) {
 * if (valor == null) throw new IllegalArgumentException("Obligatorio");
 * if (valor.length() < 7 || valor.length() > 10)
 * throw new IllegalArgumentException("Inválido");
 * 
 * this.valor = valor;
 * }
 * 
 * public String getValor() {
 * return valor;
 * }
 */
