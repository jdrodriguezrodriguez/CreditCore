package com.credito.creditcore.application.prestamo.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoRequestDto;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.score.PuntajeAntiguedad;
import com.credito.creditcore.domain.model.score.PuntajeCapacidad;
import com.credito.creditcore.domain.model.score.PuntajeEndeudamiento;

@Service
public class ScoreCrediticioService {

    /*
     * Nivel de importancia:
     * 
     * Historial : 400 pts
     * Capacidad : 300 pts
     * Endeudamiento : 200 pts
     * Antiguedad : 100 pts
     */

    private static final BigDecimal INGRESO_MUY_BAJO = BigDecimal.valueOf(1500000);
    private static final BigDecimal INGRESO_BAJO = BigDecimal.valueOf(2500000);
    private static final BigDecimal INGRESO_MEDIO = BigDecimal.valueOf(4000000);
    private static final BigDecimal INGRESO_MEDIO_ALTO = BigDecimal.valueOf(6000000);
    private static final BigDecimal INGRESO_ALTO = BigDecimal.valueOf(7500000);
    private static final BigDecimal INGRESO_MUY_ALTO = BigDecimal.valueOf(12000000);

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
        List<PuntajeCapacidad> puntajeList = List.of(
            new PuntajeCapacidad(INGRESO_MUY_ALTO, 300),
            new PuntajeCapacidad(INGRESO_ALTO, 240),
            new PuntajeCapacidad(INGRESO_MEDIO_ALTO, 180),
            new PuntajeCapacidad(INGRESO_MEDIO, 120),
            new PuntajeCapacidad(INGRESO_BAJO, 55),
            new PuntajeCapacidad(INGRESO_MUY_BAJO, 25)
        );

        for(PuntajeCapacidad puntaje : puntajeList){
            if (salario.compareTo(puntaje.getSalario()) >= 0) {
                return puntaje.getPuntaje();
            }
        }

        return 0;
    }

    public int calcularPuntajeEndeudamiento(BigDecimal salario, BigDecimal ingresosAdicional,
            BigDecimal gastosMensuales) {

        BigDecimal ingresosTotal = salario.add(ingresosAdicional);
        BigDecimal porcentajeEndeudamiento = gastosMensuales.divide(ingresosTotal, 2, RoundingMode.HALF_UP);

        List<PuntajeEndeudamiento> puntajeList = List.of(
                new PuntajeEndeudamiento(BigDecimal.valueOf(0.1), 200),
                new PuntajeEndeudamiento(BigDecimal.valueOf(0.3), 180),
                new PuntajeEndeudamiento(BigDecimal.valueOf(0.5), 140),
                new PuntajeEndeudamiento(BigDecimal.valueOf(0.7), 80),
                new PuntajeEndeudamiento(BigDecimal.valueOf(0.9), 25));

        for (PuntajeEndeudamiento puntaje : puntajeList) {
            if (porcentajeEndeudamiento.compareTo(puntaje.getLimite()) < 0) {
                return puntaje.getPuntaje();
            }
        }

        return 0;
    }

    public int calcularPuntajeAntiguedad(LocalDate fechaRegistro) {
        
        long antiguedad = ChronoUnit.YEARS.between(fechaRegistro, LocalDate.now());

        List<PuntajeAntiguedad> puntajeList = List.of(
            new PuntajeAntiguedad(5, 100),
            new PuntajeAntiguedad(4, 70),
            new PuntajeAntiguedad(3, 40),
            new PuntajeAntiguedad(2, 25),
            new PuntajeAntiguedad(1, 15)
        );

        for(PuntajeAntiguedad puntaje : puntajeList){
            if (antiguedad >= puntaje.getAntiguedad()) {
                return puntaje.getPuntaje();
            }
        }

        return 0;
    }

    public int calcularScoreTotal(Cliente cliente, SimuladorPrestamoRequestDto datos) {
        int scorePrestamo = 0;
        // HISTORIAL(CALIFICACION DE 1 - 100)
        scorePrestamo += calcularPuntajeHistorial(cliente.getHistorialCrediticio());

        scorePrestamo += calcularPuntajeCapacidad(cliente.getSalario());

        scorePrestamo += calcularPuntajeEndeudamiento(cliente.getSalario(),
                datos.ingresosAdicionales(),
                datos.gastosMensuales());

        scorePrestamo += calcularPuntajeAntiguedad(cliente.getFechaRegistro());

        return scorePrestamo;
    }
}
