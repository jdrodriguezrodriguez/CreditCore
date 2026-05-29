package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.EstadoCuota;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuota {

    private Integer idCuota;
    private Prestamo prestamo;
    private int numero_cuota;
    private LocalDate fecha_vencimiento;
    private EstadoCuota estadoCuota;

    private BigDecimal saldoInicial;
    private BigDecimal interes;
    private BigDecimal amortizacionCapital;
    private BigDecimal montoCuota;
    private BigDecimal saldoFinal;

    private BigDecimal montoPagado;
    private BigDecimal mora;
    private LocalDate fechaPagoReal;

    public Cuota() {

    }

    public Cuota(Integer idCuota, Prestamo prestamo, int numero_cuota, LocalDate fecha_vencimiento,
            EstadoCuota estadoCuota, BigDecimal saldoInicial, BigDecimal interes, BigDecimal amortizacionCapital,
            BigDecimal montoCuota, BigDecimal saldoFinal, BigDecimal montoPagado, BigDecimal mora,
            LocalDate fechaPagoReal) {

        if (montoCuota.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de la cuota debe ser mayor a $0");
        }

        this.idCuota = idCuota;
        this.prestamo = prestamo;
        this.numero_cuota = numero_cuota;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estadoCuota = estadoCuota;
        this.saldoInicial = saldoInicial;
        this.interes = interes;
        this.amortizacionCapital = amortizacionCapital;
        this.montoCuota = montoCuota;
        this.saldoFinal = saldoFinal;
        this.montoPagado = montoPagado;
        this.mora = mora;
        this.fechaPagoReal = fechaPagoReal;
    }

    public Cuota(int numero_cuota, LocalDate fecha_vencimiento,
            EstadoCuota estadoCuota, BigDecimal saldoInicial,
            BigDecimal interes, BigDecimal amortizacionCapital,
            BigDecimal montoCuota, BigDecimal saldoFinal,
            BigDecimal montoPagado, BigDecimal mora,
            LocalDate fechaPagoReal) {

        if (montoCuota.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "El monto de la cuota debe ser mayor a $0");
        }

        this.numero_cuota = numero_cuota;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estadoCuota = estadoCuota;
        this.saldoInicial = saldoInicial;
        this.interes = interes;
        this.amortizacionCapital = amortizacionCapital;
        this.montoCuota = montoCuota;
        this.saldoFinal = saldoFinal;
        this.montoPagado = montoPagado;
        this.mora = mora;
        this.fechaPagoReal = fechaPagoReal;
    }

    public static Cuota crear(Prestamo prestamo, int numero_cuota, BigDecimal montoCuota, LocalDate fecha_vencimiento,
            EstadoCuota estadoCuota, BigDecimal saldoInicial, BigDecimal interes, BigDecimal amortizacionCapital,
            BigDecimal saldoFinal, BigDecimal montoPagado, BigDecimal mora, LocalDate fechaPagoReal) {
        return new Cuota(null,
                prestamo,
                numero_cuota,
                fecha_vencimiento,
                estadoCuota,
                saldoInicial,
                interes,
                amortizacionCapital,
                montoCuota,
                saldoFinal,
                montoPagado,
                mora,
                fechaPagoReal);
    }
}