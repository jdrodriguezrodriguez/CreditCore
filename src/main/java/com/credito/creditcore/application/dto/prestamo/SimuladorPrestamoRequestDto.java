package com.credito.creditcore.application.dto.prestamo;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.TipoPrestamo;

import jakarta.validation.constraints.NotBlank;

public record SimuladorPrestamoRequestDto(
    
    @NotBlank(message = "El monto es obligatorio")
    BigDecimal monto,

    @NotBlank(message = "El plazo es obligatorio")
    int plazo,

    @NotBlank(message = "El tipo de prestamo es obligatorio")
    TipoPrestamo tipoPrestamo,

    BigDecimal ingresosAdicionales,

    @NotBlank(message = "Los gastos mensuales son obligatorios")
    BigDecimal gastosMensuales
    
) {}
