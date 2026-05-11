package com.credito.creditcore.application.dto.prestamo;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.EstimacionPuntaje;

public record SimuladorPrestamoResponseDto(
        BigDecimal monto,
        BigDecimal cuotaMensual,
        BigDecimal totalPagar,
        BigDecimal interesTotal,
        Integer plazo,
        double tasaInteres,
        int scoreCrediticio,
        EstimacionPuntaje nivelRiesgo) {
}
