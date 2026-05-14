package com.credito.creditcore.application.dto.prestamo;

import java.math.BigDecimal;

public record AmortizacionResponseDto(
        int cuota,
        BigDecimal saldoInicial,
        BigDecimal interes,
        BigDecimal amortz,
        BigDecimal pagoBigDecimal,
        BigDecimal saldoFinal) {

}
