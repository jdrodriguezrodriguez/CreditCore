package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.enums.EstadoPrestamo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class PrestamoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamo;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private ClienteEntity cliente;

    private BigDecimal monto;

    private double interes;

    @Column(name = "plazo_meses")
    private int plazo;

    @Column(name = "estado")
    private EstadoPrestamo estadoPrestamo;

    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "fecha_aprobacion")
    private LocalDate fechaAprobacion;

    @Column(name = "idTipoPrestamo")
    private int tipoPrestamo;

    public PrestamoEntity(){}

    public PrestamoEntity(Integer idPrestamo, ClienteEntity cliente, BigDecimal monto, double interes, int plazo,
            EstadoPrestamo estadoPrestamo, LocalDate fechaSolicitud, LocalDate fechaAprobacion, int tipoPrestamo) {
        this.idPrestamo = idPrestamo;
        this.cliente = cliente;
        this.monto = monto;
        this.interes = interes;
        this.plazo = plazo;
        this.estadoPrestamo = estadoPrestamo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAprobacion = fechaAprobacion;
        this.tipoPrestamo = tipoPrestamo;
    }
}
