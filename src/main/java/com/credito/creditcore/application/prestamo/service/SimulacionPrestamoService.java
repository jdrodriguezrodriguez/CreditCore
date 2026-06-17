package com.credito.creditcore.application.prestamo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.prestamo.AmortizacionResponseDto;
import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoRequestDto;
import com.credito.creditcore.application.dto.prestamo.SimuladorPrestamoResponseDto;
import com.credito.creditcore.application.prestamo.domain.service.ScoreCrediticioService;
import com.credito.creditcore.application.prestamo.port.AmortizacionFrancesaService;
import com.credito.creditcore.application.prestamo.port.SimularPrestamoUseCase;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.enums.ApprovalEstimate;
import com.credito.creditcore.domain.model.score.CuotaAmortizacion;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;

@Service
public class SimulacionPrestamoService implements SimularPrestamoUseCase {

    private static final double INTERES_BASE_MENSUAL = 0.02;
    private static final int SCORE_RECHAZADO = 160;
    private static final int SCORE_APROBACION_PARCIAL = 600;

    private final CustomerRepositoryPort clienteRepositoryPort;

    private final ScoreCrediticioService scoreCrediticioService;
    private final AmortizacionFrancesaService amortizacionFrancesaService;

    public SimulacionPrestamoService(CustomerRepositoryPort clienteRepositoryPort,
            ScoreCrediticioService scoreCrediticioService,
            AmortizacionFrancesaService amortizacionFrancesaService) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.scoreCrediticioService = scoreCrediticioService;
        this.amortizacionFrancesaService = amortizacionFrancesaService;
    }

    @Override
    public SimuladorPrestamoResponseDto simularPrestamo(SimuladorPrestamoRequestDto datos, Integer idPersona) {

        Customer cliente = clienteRepositoryPort.obtenerPorIdPersona(idPersona)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se encontro un cliente con el idPersona: " + idPersona));

        int scorePrestamo = scoreCrediticioService.calcularScoreTotal(cliente, datos);

        ApprovalEstimate estimacion = estimacionPuntaje(scorePrestamo);

        BigDecimal cuotaMensual = amortizacionFrancesaService.calcularCuotaMensual(datos.monto(), datos.plazo());
        BigDecimal totalPagar = amortizacionFrancesaService.calcularTotalPagar(datos.monto(), datos.plazo());
        BigDecimal interesTotal = amortizacionFrancesaService.calcularInteresTotal(datos.monto(), datos.plazo());

        List<CuotaAmortizacion> tabla = amortizacionFrancesaService.generarTablaAmortizacion(datos.monto(),
                datos.plazo());

        List<AmortizacionResponseDto> amortizacionResponse = tabla.stream().map(
                fila -> new AmortizacionResponseDto(
                        fila.getCuota(),
                        fila.getSaldoInicial(),
                        fila.getInteres(),
                        fila.getAmortz(),
                        fila.getMontoCuota(),
                        fila.getSaldoFinal()))
                .toList();

        return new SimuladorPrestamoResponseDto(
                datos.monto(),
                cuotaMensual,
                totalPagar,
                interesTotal,
                datos.plazo(),
                INTERES_BASE_MENSUAL,
                scorePrestamo,
                estimacion,
                datos.tipoPrestamo(), // POSIBLES AJUSTES POR EL TIPO DE PRESTAMO
                amortizacionResponse);
    }

    private ApprovalEstimate estimacionPuntaje(int scorePrestamo) {
        if (scorePrestamo < SCORE_RECHAZADO) {
            return ApprovalEstimate.REJECTION;
        } else if (scorePrestamo < SCORE_APROBACION_PARCIAL) {
            return ApprovalEstimate.POSSIBLE_APPROVAL;
        } else {
            return ApprovalEstimate.APPROVAL;
        }
    }
}
