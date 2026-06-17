package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.PaymentMethod;

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


@Getter
@Setter
@Entity
@Table(name = "pago")
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "idCuota", referencedColumnName = "idCuota")
    private CuotaEntity cuotaEntity;

    @Column(name = "monto_pagado")
    private BigDecimal montoPagado;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "metodo_pago")
    @Enumerated(EnumType.STRING)
    private PaymentMethod fpago;

    public PagoEntity(CuotaEntity cuotaEntity, BigDecimal montoPagado, LocalDate fechaPago, PaymentMethod fpago) {
        this.cuotaEntity = cuotaEntity;
        this.montoPagado = montoPagado;
        this.fechaPago = fechaPago;
        this.fpago = fpago;
    }
}
