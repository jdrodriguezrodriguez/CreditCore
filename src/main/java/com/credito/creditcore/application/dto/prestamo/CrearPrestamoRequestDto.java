package com.credito.creditcore.application.dto.prestamo;

import java.math.BigDecimal;
import java.util.List;

import com.credito.creditcore.domain.model.enums.ApprovalEstimate;
import com.credito.creditcore.domain.model.enums.LoanType;

public record CrearPrestamoRequestDto(

        BigDecimal monto,
        BigDecimal cuotaMensual,
        BigDecimal totalPagar,
        BigDecimal interesTotal,
        Integer plazo,
        double tasaInteres,
        int scoreCrediticio,
        ApprovalEstimate nivelRiesgo,
        LoanType tipoPrestamo,
        List<AmortizacionResponseDto> amortizacionResponseDto) {

}
