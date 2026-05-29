package com.credito.creditcore.application.cuota.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.cuota.port.ObtenerCuotasUseCase;
import com.credito.creditcore.application.prestamo.port.AmortizacionFrancesaService;
import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;
import com.credito.creditcore.domain.port.CuotaRepositoryPort;
import com.credito.creditcore.domain.port.PrestamorepositoryPort;

@Service
public class ObtenerCuotasService implements ObtenerCuotasUseCase {

    private final CuotaRepositoryPort cuotaRepositoryPort;


    public ObtenerCuotasService(CuotaRepositoryPort cuotaRepositoryPort) {
        this.cuotaRepositoryPort = cuotaRepositoryPort;
    }

    @Override
    public List<Cuota> obtenerCuotas(Integer idCliente) {

        List<Cuota> cuota = cuotaRepositoryPort.obtenerCuotasPorIdPrestamo(idCliente);

        if (cuota.isEmpty()) {
            new IllegalArgumentException("Lista vacia, no se encontraron cuotas");
        }
        
        return cuota;
    }
}
