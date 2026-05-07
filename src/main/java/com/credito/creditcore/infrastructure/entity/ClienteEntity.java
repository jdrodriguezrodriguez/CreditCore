package com.credito.creditcore.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClienteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;

    @OneToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private PersonaEntity persona;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Column(name = "score_crediticio", nullable = false)
    private Integer historialCrediticio;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    public ClienteEntity(){}

    public ClienteEntity(PersonaEntity persona, BigDecimal salario, Integer historialCrediticio,
            LocalDate fechaRegistro) {
        this.persona = persona;
        this.salario = salario;
        this.historialCrediticio = historialCrediticio;
        this.fechaRegistro = fechaRegistro;
    }
}
