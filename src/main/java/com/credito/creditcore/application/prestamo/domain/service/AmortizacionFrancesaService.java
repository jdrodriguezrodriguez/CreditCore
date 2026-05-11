package com.credito.creditcore.application.prestamo.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoRequestDto;

@Service
public class AmortizacionFrancesaService {

    private final double INTERES_BASE_MENSUAL = 0.02;

    /*
     * C = cuota mensual
     * c = capital
     * P = préstamo
     * r = interés mensual (tasa)
     * n = número de cuotas
     * t = total Pagado
     */

    // Cuota Mensual: c = p * r(1 + r)^n / (1 + r)^n - 1
    public BigDecimal calcularCuotaMensual(SimuladorPrestamoRequestDto datos) {

        BigDecimal tasa = BigDecimal.valueOf(INTERES_BASE_MENSUAL);
        BigDecimal UnoMasTasa = BigDecimal.valueOf(1).add(tasa);
        BigDecimal potencia = UnoMasTasa.pow(datos.plazo());

        BigDecimal numerador = tasa.multiply(potencia);
        BigDecimal denominador = potencia.subtract(BigDecimal.valueOf(1));

        BigDecimal cuotaMensual = datos.monto().multiply(numerador).divide(denominador, 2, RoundingMode.HALF_UP);

        return cuotaMensual;
    }

    public BigDecimal calcularTotalPagar(SimuladorPrestamoRequestDto datos) {
        return calcularCuotaMensual(datos).multiply(BigDecimal.valueOf(datos.plazo()));
    }

    // I = t - P
    public BigDecimal calcularInteresTotal(SimuladorPrestamoRequestDto datos){
        return calcularTotalPagar(datos).subtract(datos.monto());
    }

    /*
     * // I = c * r * t
     * public BigDecimal calcularInteresSimple(SimuladorPrestamoDto datos) {
     * 
     * BigDecimal tiempoAnual =
     * BigDecimal.valueOf(datos.plazo()).divide(BigDecimal.valueOf(12), 2,
     * RoundingMode.HALF_UP);
     * 
     * BigDecimal interesAnual =
     * BigDecimal.valueOf(INTERES_BASE_MENSUAL).multiply(BigDecimal.valueOf(12));
     * 
     * 
     * return datos.monto().multiply(interesAnual)
     * .multiply(tiempoAnual);
     * }
     */
}
