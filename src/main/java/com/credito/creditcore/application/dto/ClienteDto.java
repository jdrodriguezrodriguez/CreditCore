package com.credito.creditcore.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record ClienteDto(

        @NotBlank(message = "El salario es obligatorio.")
        BigDecimal salario,

        @NotBlank(message = "El historial financiero es obligatorio.") 
        Integer scoreCrediticio,

        LocalDate fechaRegistro) {

}
