package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.LoanStatus;
import com.credito.creditcore.domain.model.enums.LoanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prestamo")
@Getter
@Setter
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamo;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private CustomerEntity cliente;

    private BigDecimal monto;

    private double interes;

    @Column(name = "plazo_meses")
    private int plazo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private LoanStatus estadoPrestamo;

    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "fecha_aprobacion")
    private LocalDate fechaAprobacion;

    @Column(name = "tipo_prestamo")
    @Enumerated(EnumType.STRING)
    private LoanType tipoPrestamo;

    @Column(name = "interes_total")
    private BigDecimal interesTotal;

    @Column(name = "total_pagar")
    private BigDecimal totalPagar;

    @Column(name = "total_pagado")
    private BigDecimal totalPagado;

    @Column(name = "saldo_pendiente")
    private BigDecimal saldoPendiente;

    public LoanEntity() {
    }

    public LoanEntity(CustomerEntity cliente, BigDecimal monto, double interes, int plazo,
            LoanStatus estadoPrestamo, LocalDate fechaSolicitud, LocalDate fechaAprobacion,
            LoanType tipoPrestamo, BigDecimal interesTotal, BigDecimal totalPagar, BigDecimal totalPagado,
            BigDecimal saldoPendiente) {
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
}
