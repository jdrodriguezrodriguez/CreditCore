package com.credito.creditcore.application.dto.cliente;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record SalarioClienteDto(

        @NotBlank(message = "El salario es obligatorio.")
        BigDecimal salario
) {
}
