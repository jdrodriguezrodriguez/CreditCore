package com.credito.creditcore.domain.port;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.domain.model.enums.Fpago;

public interface PagoRepositoryPort {
    void guardarFpago(Cuota cuota, Fpago fpago, BigDecimal monto);
}
