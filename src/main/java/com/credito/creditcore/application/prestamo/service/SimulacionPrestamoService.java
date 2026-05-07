package com.credito.creditcore.application.prestamo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoDto;
import com.credito.creditcore.application.prestamo.port.SimularPrestamoUseCase;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;
import com.credito.creditcore.domain.port.PrestamorepositoryPort;

public class SimulacionPrestamoService implements SimularPrestamoUseCase {

    private final PrestamorepositoryPort prestamorepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    private final double INTERES_BASE_MENSUAL = 0.02;

    private final BigDecimal INGRESO_ALTO = BigDecimal.valueOf(7490000);
    private final BigDecimal INGRESO_MEDIO = BigDecimal.valueOf(3990000);

    public SimulacionPrestamoService(PrestamorepositoryPort PrestamorepositoryPort,
            ClienteRepositoryPort clienteRepositoryPort) {
        this.prestamorepositoryPort = PrestamorepositoryPort;
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    /*
     * Historial : 400 pts
     * Capacidad : 300 pts
     * Endeudamiento : 200 pts
     * Antiguedad : 100 pts
     */

    @Override
    public Prestamo simulacroPrestamo(SimuladorPrestamoDto datos, Integer idPersona) {

        int scorePrestamo = 0;

        Cliente cliente = clienteRepositoryPort.obtenerPorIdPersona(idPersona)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se encontro un cliente con el idPersona: " + idPersona));

        scorePrestamo += calcularPuntajeHistorial(cliente.getHistorialCrediticio()); // HISTORIAL (CALIFICACION DE 1 -
                                                                                     // 100)

        scorePrestamo += calcularPuntajeCapacidad(cliente.getSalario());

        scorePrestamo += calcularPuntajeEndeudamiento(cliente.getSalario(), datos.ingresosAdicionales(),
                datos.gastosMensuales());

        scorePrestamo += calcularPuntajeAntiguedad(cliente.getFechaRegistro());

        if (scorePrestamo < 160) {
            throw new IllegalArgumentException("Estimacion - Rechazo");
        } else if (scorePrestamo < 600) {
            throw new IllegalArgumentException("Estimacion - Posible aprobacion");
        } else {
            throw new IllegalArgumentException("Estimacion - Aprobacion");
        }
    }

    public int calcularPuntajeHistorial(Integer historialCrediticio) {
        int score = 0;

        // EL HISTORIAL CREDITICIO TIENE UN RANGO DE [0 , 100]
        if (historialCrediticio != null) {
            Double historial = (historialCrediticio / 10.0);

            score = historial >= 9 ? 400
                    : historial >= 6 ? 150
                            : historial >= 3 ? 60
                                    : 0;
        }

        return score;
    }

    public int calcularPuntajeCapacidad(BigDecimal salario) {
        int score = 0;

        if (salario.compareTo(INGRESO_ALTO) > 0) {
            score += 300;
        } else if (salario.compareTo(INGRESO_MEDIO) > 0) {
            score += 150;
        } else {
            score += 0;
        }

        return score;
    }

    public int calcularPuntajeEndeudamiento(BigDecimal salario, BigDecimal ingresosAdicional,
            BigDecimal gastosMensuales) {
        int score = 0;
        BigDecimal ingresosTotal = salario.add(ingresosAdicional);

        if (gastosMensuales.divide(ingresosTotal).compareTo(BigDecimal.valueOf(0.3)) < 0) {
            score += 200;
        } else if (gastosMensuales.divide(ingresosTotal).compareTo(BigDecimal.valueOf(0.5)) < 0) {
            score += 100;
        } else {
            score += 0;
        }

        return score;
    }

    public int calcularPuntajeAntiguedad(LocalDate fechaRegistro) {
        int score = 0;

        LocalDate ANTIGUEDAD_5 = fechaRegistro.plusYears(5);
        LocalDate ANTIGUEDAD_1 = fechaRegistro.plusYears(1);

        if (ANTIGUEDAD_5.isBefore(LocalDate.now())) {
            score += 100;
        } else if (ANTIGUEDAD_1.isBefore(LocalDate.now())) {
            score += 50;
        } else {
            score += 0;
        }

        return score;
    }

    // Cuota Mensual: c = p * r(1 + r)^n / (1 + r)^n - 1
    public BigDecimal calcularCuotaMensual(SimuladorPrestamoDto datos) {

        BigDecimal tasa = BigDecimal.valueOf(INTERES_BASE_MENSUAL);
        BigDecimal UnoMasTasa = BigDecimal.valueOf(1).add(tasa);
        BigDecimal potencia = UnoMasTasa.pow(datos.plazo());

        BigDecimal numerador = tasa.multiply(potencia);
        BigDecimal denominador = potencia.subtract(BigDecimal.valueOf(1));

        BigDecimal cuotaMensual = datos.monto().multiply(numerador).divide(denominador, 2, RoundingMode.HALF_UP);

        return cuotaMensual;
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

    public BigDecimal calcularTotalPagar(SimuladorPrestamoDto datos) {
        return calcularCuotaMensual(datos).multiply(BigDecimal.valueOf(datos.plazo()));
    }

    /*
     * C = cuota mensual
     * c = capital
     * P = préstamo
     * r = interés mensual (tasa)
     * n = número de cuotas
     */

}
