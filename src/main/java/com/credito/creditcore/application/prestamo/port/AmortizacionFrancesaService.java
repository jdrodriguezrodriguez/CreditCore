package com.credito.creditcore.application.prestamo.port;

import java.math.BigDecimal;
import java.util.List;

import com.credito.creditcore.domain.model.score.CuotaAmortizacion;

public interface AmortizacionFrancesaService {
    public BigDecimal calcularCuotaMensual(BigDecimal monto, int plazo);

    public BigDecimal calcularTotalPagar(BigDecimal monto, int plazo);

    public BigDecimal calcularInteresTotal(BigDecimal monto, int plazo);

    public List<CuotaAmortizacion> generarTablaAmortizacion(BigDecimal monto, int plazo);
}
