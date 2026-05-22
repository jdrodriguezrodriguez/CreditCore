package com.credito.creditcore.domain.model.score;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuotaAmortizacion {

    int cuota = 0;
    BigDecimal saldoInicial = BigDecimal.ZERO;
    BigDecimal interes = BigDecimal.ZERO;
    BigDecimal amortz = BigDecimal.ZERO;
    BigDecimal montoCuota = BigDecimal.ZERO;
    BigDecimal saldoFinal = BigDecimal.ZERO;
    
    public CuotaAmortizacion(){}

    public CuotaAmortizacion(int cuota, BigDecimal saldoInicial, BigDecimal interes, BigDecimal amortz, BigDecimal montoCuota,
            BigDecimal saldoFinal) {
        this.cuota = cuota;
        this.saldoInicial = saldoInicial;
        this.interes = interes;
        this.amortz = amortz;
        this.montoCuota = montoCuota;
        this.saldoFinal = saldoFinal;
    }
}
