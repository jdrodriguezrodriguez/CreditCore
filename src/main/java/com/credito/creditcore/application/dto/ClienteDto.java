package com.credito.creditcore.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.Persona;

public record ClienteDto(

    Integer idCliente,
    Persona persona,
    BigDecimal salario,
    Integer score,
    LocalDate fechaRegistro
    
) {}