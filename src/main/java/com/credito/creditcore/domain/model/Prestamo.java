package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.EstadoPrestamo;
import com.credito.creditcore.domain.model.enums.TipoPrestamo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prestamo {

    private Integer idPrestamo;
    private Cliente cliente;
    private BigDecimal monto;
    private double interes;
    private int plazo;
    private EstadoPrestamo estadoPrestamo;
    private LocalDate fechaSolicitud;
    private LocalDate fechaAprobacion;
    private TipoPrestamo tipoPrestamo;

    private BigDecimal interesTotal;
    private BigDecimal totalPagar;
    private BigDecimal totalPagado;
    private BigDecimal saldoPendiente;

    public Prestamo() {

    }

    public Prestamo(Integer idPrestamo, Cliente cliente, BigDecimal monto, double interes, int plazo,
            EstadoPrestamo estadoPrestamo, LocalDate fechaSolicitud, LocalDate fechaAprobacion,
            TipoPrestamo tipoPrestamo, BigDecimal interesTotal, BigDecimal totalPagar, BigDecimal totalPagado,
            BigDecimal saldoPendiente) {

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio.");
        }

        if (plazo >= 24) {
            throw new IllegalArgumentException("El plazo no puede ser superior a dos años.");
        }

        if (monto.compareTo(BigDecimal.ZERO) <= 0 && totalPagar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        this.idPrestamo = idPrestamo;
        this.cliente = cliente;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.estadoPrestamo = estadoPrestamo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAprobacion = fechaAprobacion;
        this.tipoPrestamo = tipoPrestamo;
        this.interesTotal = interesTotal;
        this.totalPagar = totalPagar;
        this.totalPagado = totalPagado;
        this.saldoPendiente = saldoPendiente;
    }

    public Prestamo(Integer idPrestamo, BigDecimal monto, double interes, int plazo,
            EstadoPrestamo estadoPrestamo, LocalDate fechaSolicitud, LocalDate fechaAprobacion,
            TipoPrestamo tipoPrestamo, BigDecimal interesTotal, BigDecimal totalPagar,
            BigDecimal totalPagado, BigDecimal saldoPendiente) {

        if (plazo >= 24) {
            throw new IllegalArgumentException("El plazo no puede ser superior a dos años.");
        }

        if (monto.compareTo(BigDecimal.ZERO) <= 0 || totalPagar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto y el total a pagar deben ser mayores a cero");
        }

        this.idPrestamo = idPrestamo;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.estadoPrestamo = estadoPrestamo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAprobacion = fechaAprobacion;
        this.tipoPrestamo = tipoPrestamo;
        this.interesTotal = interesTotal;
        this.totalPagar = totalPagar;
        this.totalPagado = totalPagado;
        this.saldoPendiente = saldoPendiente;
    }

    public static Prestamo crear(Cliente cliente, BigDecimal monto,
            double interes, int plazo, EstadoPrestamo estadoPrestamo, LocalDate fechaSolicitud,
            LocalDate fechaAprobacion, TipoPrestamo tipoPrestamo, BigDecimal interesTotal, BigDecimal totalPagar,
            BigDecimal totalPagado, BigDecimal saldoPendiente) {

        return new Prestamo(null,
                cliente,
                monto,
                interes,
                plazo,
                estadoPrestamo,
                fechaSolicitud,
                fechaAprobacion,
                tipoPrestamo,
                interesTotal,
                totalPagar,
                totalPagado,
                saldoPendiente);
    }
}
