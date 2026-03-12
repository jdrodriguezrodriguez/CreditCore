package com.credito.creditcore.domain.model;

import com.credito.creditcore.domain.model.enums.RolUsuario;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Usuario {
    
    private Integer idUsuario;
    private Integer idPersona;

    private String username;
    private String password;
    
    private RolUsuario rolUsuario;

    private boolean is_enabled;
    private boolean account_no_expired;
    private boolean account_no_locked;
    private boolean credential_no_expired;

    public Usuario(Integer idUsuario, Integer idPersona, String username, String password, RolUsuario rolUsuario,
            boolean is_enabled, boolean account_no_expired, boolean account_no_locked, boolean credential_no_expired) {
        this.idUsuario = idUsuario;
        this.idPersona = idPersona;
        this.username = username;
        this.password = password;
        this.rolUsuario = rolUsuario;
        this.is_enabled = is_enabled;
        this.account_no_expired = account_no_expired;
        this.account_no_locked = account_no_locked;
        this.credential_no_expired = credential_no_expired;
    }
}
