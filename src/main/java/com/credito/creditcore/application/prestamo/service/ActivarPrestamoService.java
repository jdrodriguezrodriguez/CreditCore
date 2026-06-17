package com.credito.creditcore.application.prestamo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.prestamo.port.ActivarPrestamoUseCase;
import com.credito.creditcore.application.prestamo.port.AmortizacionFrancesaService;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.model.enums.InstallmentStatus;
import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.model.score.CuotaAmortizacion;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.domain.port.LoanRepositoryPort;

@Service
public class ActivarPrestamoService implements ActivarPrestamoUseCase {

    private final LoanRepositoryPort prestamorepositoryPort;
    private final CustomerRepositoryPort clienteRepositoryPort;
    private final InstallmentRepositoryPort cuotaRepositoryPort;

    private final AmortizacionFrancesaService amortizacionFrancesaService;

    public ActivarPrestamoService(LoanRepositoryPort prestamorepositoryPort,
            CustomerRepositoryPort clienteRepositoryPort, InstallmentRepositoryPort cuotaRepositoryPort,
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

        Loan prestamo = prestamorepositoryPort.obtenerPorIdCliente(idPersona)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se encontro un cliente con el idPersona: " + idPersona));

        prestamo.setApprovalDate(LocalDate.now());
        prestamo.setLoanStatus(LoanStatus.ACTIVE);

        List<Installment> cuotas = new ArrayList<>();
        List<CuotaAmortizacion> tabla = amortizacionFrancesaService.generarTablaAmortizacion(prestamo.getPrincipalAmount(),
                prestamo.getTermInMonths());

        for (int i = 0; i < prestamo.getTermInMonths(); i++) {

            CuotaAmortizacion cuotaAmortizacion = tabla.get(i);
            LocalDate fechaLimiteCuota = prestamo.getApprovalDate().plusMonths(i + 1);

            Installment cuota = new Installment(
                    null,
                    prestamo,
                    i + 1,
                    fechaLimiteCuota,
                    InstallmentStatus.PENDING,
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
