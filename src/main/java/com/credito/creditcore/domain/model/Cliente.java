package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Cliente {

    private Integer idCliente;
    private Persona persona;
    private BigDecimal salario;
    private Integer historialCrediticio;
    private LocalDate fechaRegistro;

    public Cliente() {

    }

    public Cliente(Integer idCliente, Persona persona, BigDecimal salario, Integer historialCrediticio,
            LocalDate fechaRegistro) {

        if (persona == null) {
            throw new IllegalArgumentException("La persona es obligatoria.");
        }

        if (salario == null || historialCrediticio == null) {
            throw new IllegalArgumentException("Los datos financieros son obligatorios.");
        }

        this.idCliente = idCliente;
        this.persona = persona;
        this.salario = salario;
        this.historialCrediticio = historialCrediticio;
        this.fechaRegistro = fechaRegistro;
    }
}
