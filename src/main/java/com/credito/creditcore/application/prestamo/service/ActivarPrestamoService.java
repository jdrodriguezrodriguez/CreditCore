package com.credito.creditcore.application.prestamo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.prestamo.port.ActivarPrestamoUseCase;
import com.credito.creditcore.application.prestamo.port.AmortizacionFrancesaService;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.model.enums.EstadoCuota;
import com.credito.creditcore.domain.model.enums.EstadoPrestamo;
import com.credito.creditcore.domain.model.score.CuotaAmortizacion;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.domain.port.CuotaRepositoryPort;
import com.credito.creditcore.domain.port.PrestamorepositoryPort;

@Service
public class ActivarPrestamoService implements ActivarPrestamoUseCase {

    private final PrestamorepositoryPort prestamorepositoryPort;
    private final CustomerRepositoryPort clienteRepositoryPort;
    private final CuotaRepositoryPort cuotaRepositoryPort;

    private final AmortizacionFrancesaService amortizacionFrancesaService;

    public ActivarPrestamoService(PrestamorepositoryPort prestamorepositoryPort,
            CustomerRepositoryPort clienteRepositoryPort, CuotaRepositoryPort cuotaRepositoryPort,
            AmortizacionFrancesaService amortizacionFrancesaService) {
        this.prestamorepositoryPort = prestamorepositoryPort;
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.cuotaRepositoryPort = cuotaRepositoryPort;
        this.amortizacionFrancesaService = amortizacionFrancesaService;
    }

    @Override
    public void activarPrestamo(int idPersona) {

        Customer cliente = clienteRepositoryPort.obtenerPorIdPersona(idPersona)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se encontro un cliente con el idPersona: " + idPersona));

        Prestamo prestamo = prestamorepositoryPort.obtenerPorIdCliente(idPersona)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se encontro un cliente con el idPersona: " + idPersona));

        prestamo.setFechaAprobacion(LocalDate.now());
        prestamo.setEstadoPrestamo(EstadoPrestamo.ACTIVO);

        List<Cuota> cuotas = new ArrayList<>();
        List<CuotaAmortizacion> tabla = amortizacionFrancesaService.generarTablaAmortizacion(prestamo.getMonto(),
                prestamo.getPlazo());

        for (int i = 0; i < prestamo.getPlazo(); i++) {

            CuotaAmortizacion cuotaAmortizacion = tabla.get(i);
            LocalDate fechaLimiteCuota = prestamo.getFechaAprobacion().plusMonths(i + 1);

            Cuota cuota = new Cuota(
                    null,
                    prestamo,
                    i + 1,
                    fechaLimiteCuota,
                    EstadoCuota.PENDIENTE,
                    cuotaAmortizacion.getSaldoInicial(),
                    cuotaAmortizacion.getInteres(),
                    cuotaAmortizacion.getAmortz(),
                    cuotaAmortizacion.getMontoCuota(),
                    cuotaAmortizacion.getSaldoFinal(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    null);

            cuotas.add(cuota);
        }

        prestamorepositoryPort.actualizar(idPersona, prestamo);
        cuotaRepositoryPort.guardarCuotas(cuotas, prestamo, cliente);
    }
}
