package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Customer {

    private Integer idCliente;
    private Person persona;
    private BigDecimal salario;
    private Integer historialCrediticio;
    private LocalDate fechaRegistro;

    public Customer() {

    }

    public Customer(Integer idCliente, Person persona, BigDecimal salario, Integer historialCrediticio,
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

    public static Customer createCustomer(BigDecimal salario, Integer scoreCrediticio, Person persona){
        return new Customer(null, 
            persona, 
            salario, 
            scoreCrediticio, 
            LocalDate.now());
    }
}
