package com.credito.creditcore.application.dto.cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.Person;

public record ClienteDto(

    Integer idCliente,
    Person persona,
    BigDecimal salario,
    Integer score,
    LocalDate fechaRegistro
    
) {}