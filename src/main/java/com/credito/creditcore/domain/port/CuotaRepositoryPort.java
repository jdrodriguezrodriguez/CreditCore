package com.credito.creditcore.domain.port;

import java.util.List;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.domain.model.Prestamo;

public interface CuotaRepositoryPort {
    void guardar(List<Cuota> cuotas, Prestamo prestamo, Cliente cliente);
}
