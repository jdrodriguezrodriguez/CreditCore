package com.credito.creditcore.application.cuota.port;

import java.util.List;

import com.credito.creditcore.domain.model.Cuota;

public interface ObtenerCuotasUseCase {
    List<Cuota> obtenerCuotas(Integer idPrestamo);
}
