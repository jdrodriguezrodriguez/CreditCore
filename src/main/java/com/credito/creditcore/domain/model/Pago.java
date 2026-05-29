package com.credito.creditcore.domain.model;

import java.math.BigDecimal;

import com.credito.creditcore.domain.model.enums.Fpago;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pago {

    private Integer idPago;
    private Cuota cuota;
    private BigDecimal montoPagado;
    private Fpago fpago;

    public Pago() {
    }

    public Pago(Cuota cuota, BigDecimal montoPagado, Fpago fpago) {

        if (montoPagado.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a $0");
        }

        this.cuota = cuota;
        this.montoPagado = montoPagado;
        this.fpago = fpago;
    }

    public static Pago crear(Cuota cuota, BigDecimal montoPagado, Fpago fpago) {
        return new Pago(cuota, montoPagado, fpago);
    }
}
