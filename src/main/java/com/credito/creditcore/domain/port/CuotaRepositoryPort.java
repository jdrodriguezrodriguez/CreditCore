package com.credito.creditcore.domain.port;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.domain.model.Prestamo;

public interface CuotaRepositoryPort {
    void guardarCuotas(List<Cuota> cuotas, Prestamo prestamo, Customer cliente);
    Optional<Cuota> obtenerCuota(Integer idCuota);
    List<Cuota> obtenerCuotasPorIdPrestamo(Integer idCliente);
    void actualizarCuota(Integer idCuota, BigDecimal monto);
}
