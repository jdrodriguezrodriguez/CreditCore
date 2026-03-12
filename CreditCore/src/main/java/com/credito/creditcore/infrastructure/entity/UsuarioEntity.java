package com.credito.creditcore.infrastructure.entity;

import com.credito.creditcore.domain.model.enums.RolUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false)
    @JoinColumn(name = "idPersona",referencedColumnName = "idPersona")
    private PersonaEntity persona;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "rol", nullable = false)
    private RolUsuario rolUsuario;

    @Column(name = "is_enabled", nullable = false)
    private boolean is_enabled;

    @Column(name = "account_no_expired", nullable = false)
    private boolean account_no_expired;

    @Column(name = "account_no_locked", nullable = false)
    private boolean account_no_locked;

    @Column(name = "credential_no_expired", nullable = false)
    private boolean credential_no_expired;

    public UsuarioEntity(){
    }

    public UsuarioEntity(PersonaEntity persona, String username, String password,
            RolUsuario rolUsuario, boolean is_enabled, boolean account_no_expired, boolean account_no_locked,
            boolean credential_no_expired) {
        this.persona = persona;
        this.username = username;
        this.password = password;
        this.rolUsuario = rolUsuario;
        this.is_enabled = is_enabled;
        this.account_no_expired = account_no_expired;
        this.account_no_locked = account_no_locked;
        this.credential_no_expired = credential_no_expired;
    }
}
