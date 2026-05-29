package com.credito.creditcore.application.cuota.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.cuota.port.PagarCuotaUseCase;
import com.credito.creditcore.application.dto.cuota.PagarCuotaRequestDto;
import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.domain.port.CuotaRepositoryPort;
import com.credito.creditcore.domain.port.PagoRepositoryPort;

@Service
public class PagarCuotaService implements PagarCuotaUseCase {

    private final CuotaRepositoryPort cuotaRepositoryPort;
    private final PagoRepositoryPort pagoRepositoryPort;

    public PagarCuotaService(CuotaRepositoryPort cuotaRepositoryPort, PagoRepositoryPort pagoRepositoryPort) {
        this.cuotaRepositoryPort = cuotaRepositoryPort;
        this.pagoRepositoryPort = pagoRepositoryPort;
    }

    @Override
    public void pagarCuota(Integer idCuota, PagarCuotaRequestDto datos) {

        Cuota cuotaExistente = cuotaRepositoryPort.obtenerCuota(idCuota)
                .orElseThrow(() -> new IllegalArgumentException("Cuota no encontrada con el Id " + idCuota));

        if(datos.montoPagar().compareTo(BigDecimal.ZERO) < 0){
            new IllegalArgumentException("El monto debe ser mayor a $0");
        }

        cuotaRepositoryPort.actualizarCuota(idCuota, datos.montoPagar());
        pagoRepositoryPort.guardarFpago(cuotaExistente, datos.fpago(), datos.montoPagar());
    }
}