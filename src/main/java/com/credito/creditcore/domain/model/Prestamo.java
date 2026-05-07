package com.credito.creditcore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.EstadoPrestamo;

import lombok.Getter;

@Getter
public class Prestamo {

    private Integer idPrestamo;
    private Cliente cliente;
    private BigDecimal monto;
    private double interes;
    private int plazo;
    private EstadoPrestamo estadoPrestamo;
    private LocalDate fechaSolicitud;
    private LocalDate fechaAprobacion;
    private String tipoPrestamo;

    public Prestamo(){}

    public Prestamo(Integer idPrestamo, Cliente cliente, BigDecimal monto, double interes, int plazo,
            EstadoPrestamo estadoPrestamo, LocalDate fechaSolicitud, LocalDate fechaAprobacion, String tipoPrestamo) {

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio.");
        }

        if (plazo >= 24) {
            throw new IllegalArgumentException("El plazo no puede ser superior a dos años.");
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
    }

    // Simular 
    // Crear 
    // Aprobar (Genera cuotas)
    // Rechazar/Activar 
    // Pagar 
    // Finalizar

    /* public static Prestamo CrearPrestamo(Cliente cliente, ){
        return new Prestamo(null, 
            null, null, 0, 0, null, null, null, null);
    } */

}
