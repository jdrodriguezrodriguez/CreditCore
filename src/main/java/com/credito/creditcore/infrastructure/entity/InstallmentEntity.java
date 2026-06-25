package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.InstallmentStatus;

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
@Table(name = "cuota")
@Getter
@Setter
public class InstallmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuota;

    @ManyToOne
    @JoinColumn(name = "idPrestamo", referencedColumnName = "idPrestamo")
    private LoanEntity prestamo;

    @Column(name = "numero_cuota")
    private int numeroCuota;

    @Column(name = "monto")
    private BigDecimal montoCuota;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private InstallmentStatus estadoCuota;

    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;

    @Column(name = "interes")
    private BigDecimal interes;

    @Column(name = "amortizacionCapital")
    private BigDecimal amortizacionCapital;

    @Column(name = "saldo_final")
    private BigDecimal saldoFinal;

    @Column(name = "monto_pagado")
    private BigDecimal montoPagado;

    @Column(name = "mora")
    private BigDecimal mora;

    @Column(name = "fecha_pago_real")
    private LocalDate fechaPagoReal;

    public InstallmentEntity() {
    }

    public InstallmentEntity(LoanEntity prestamo, int numeroCuota, BigDecimal montoCuota,
            LocalDate fechaVencimiento, InstallmentStatus estadoCuota, BigDecimal saldoInicial, BigDecimal interes,
            BigDecimal amortizacionCapital, BigDecimal saldoFinal, BigDecimal montoPagado, BigDecimal mora,
            LocalDate fechaPagoReal) {
        this.prestamo = prestamo;
        this.numeroCuota = numeroCuota;
        this.montoCuota = montoCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.estadoCuota = estadoCuota;
        this.saldoInicial = saldoInicial;
        this.interes = interes;
        this.amortizacionCapital = amortizacionCapital;
        this.saldoFinal = saldoFinal;
        this.montoPagado = montoPagado;
        this.mora = mora;
        this.fechaPagoReal = fechaPagoReal;
    }
}
