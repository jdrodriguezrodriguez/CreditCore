package com.credito.creditcore.application.prestamo.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoRequestDto;
import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoResponseDto;
import com.credito.creditcore.application.prestamo.domain.service.AmortizacionFrancesaService;
import com.credito.creditcore.application.prestamo.domain.service.ScoreCrediticioService;
import com.credito.creditcore.application.prestamo.port.SimularPrestamoUseCase;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.enums.EstimacionPuntaje;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;

@Service
public class SimulacionPrestamoService implements SimularPrestamoUseCase {

    private static final double INTERES_BASE_MENSUAL = 0.02;
    private static final int SCORE_RECHAZADO = 160;
    private static final int SCORE_APROBACION_PARCIAL = 600;

    private final ClienteRepositoryPort clienteRepositoryPort;

    private final ScoreCrediticioService scoreCrediticioService;
    private final AmortizacionFrancesaService amortizacionFrancesaService;

    public SimulacionPrestamoService(ClienteRepositoryPort clienteRepositoryPort,
            ScoreCrediticioService scoreCrediticioService,
            AmortizacionFrancesaService amortizacionFrancesaService) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.scoreCrediticioService = scoreCrediticioService;
        this.amortizacionFrancesaService = amortizacionFrancesaService;
    }

    @Override
    public SimuladorPrestamoResponseDto simularPrestamo(SimuladorPrestamoRequestDto datos, Integer idPersona) {

        Cliente cliente = clienteRepositoryPort.obtenerPorIdPersona(idPersona)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se encontro un cliente con el idPersona: " + idPersona));

        int scorePrestamo = scoreCrediticioService.calcularScoreTotal(cliente, datos);

        EstimacionPuntaje estimacion = estimacionPuntaje(scorePrestamo);

        BigDecimal cuotaMensual = amortizacionFrancesaService.calcularCuotaMensual(datos);
        BigDecimal totalPagar = amortizacionFrancesaService.calcularTotalPagar(datos);
        BigDecimal interesTotal = amortizacionFrancesaService.calcularInteresTotal(datos);

        return new SimuladorPrestamoResponseDto(
                datos.monto(),
                cuotaMensual,
                totalPagar,
                interesTotal,
                datos.plazo(),
                INTERES_BASE_MENSUAL,
                scorePrestamo,
                estimacion);
    }

    private EstimacionPuntaje estimacionPuntaje(int scorePrestamo) {
        if (scorePrestamo < SCORE_RECHAZADO) {
            return EstimacionPuntaje.RECHAZO;
        } else if (scorePrestamo < SCORE_APROBACION_PARCIAL) {
            return EstimacionPuntaje.POSIBLE_APROBACION;
        } else {
            return EstimacionPuntaje.APROBACION;
        }
    }
}
