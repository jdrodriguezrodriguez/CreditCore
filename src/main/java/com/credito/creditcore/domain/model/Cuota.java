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
    private BigDecimal monto;
    private LocalDate fecha_vencimiento;
    private EstadoCuota estadoCuota;

    public Cuota() {

    }

    public Cuota(Integer idCuota, Prestamo prestamo, int numero_cuota, BigDecimal monto, LocalDate fecha_vencimiento,
            EstadoCuota estadoCuota) {

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de la cuota debe ser mayor a $0");
        }
        this.idCuota = idCuota;
        this.prestamo = prestamo;
        this.numero_cuota = numero_cuota;
        this.monto = monto;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estadoCuota = estadoCuota;
    }

    public static Cuota crear(Prestamo prestamo, int numero_cuota, BigDecimal monto, LocalDate fecha_vencimiento,
            EstadoCuota estadoCuota) {
        return new Cuota(null,
                prestamo,
                numero_cuota,
                monto,
                fecha_vencimiento,
                estadoCuota);
    }
}
