package com.credito.creditcore.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private Integer idPersona;

    /* @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private UsuarioEntity usuario; */

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "documento", nullable = false, unique = true)
    private String documento;

    @Column(name = "nacimiento", nullable = false)
    private LocalDate nacimiento;

    @Column(name = "correo", nullable = false)
    private String correo;

    public PersonaEntity(){
    }

    public PersonaEntity(String nombre, String apellido, String documento,
            LocalDate nacimiento, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.nacimiento = nacimiento;
        this.correo = correo;
    }
}
