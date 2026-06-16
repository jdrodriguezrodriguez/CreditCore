package com.credito.creditcore.domain.model;

import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private Integer idUsuario;
    private Person persona;

    private String username;
    private String password;

    private UserRole rolUsuario;

    private boolean is_enabled;
    private boolean account_no_expired;
    private boolean account_no_locked;
    private boolean credential_no_expired;

    public User(){}

    public User(Integer idUsuario, Person persona, String username, String password, UserRole rolUsuario,
            boolean is_enabled, boolean account_no_expired, boolean account_no_locked, boolean credential_no_expired) {

        if (persona == null) {
            throw new IllegalArgumentException("La persona es obligatoria.");
        }

        if (password == null) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }

        this.idUsuario = idUsuario;
        this.persona = persona;
        this.username = username;
        this.password = password;
        this.rolUsuario = rolUsuario;
        this.is_enabled = is_enabled;
        this.account_no_expired = account_no_expired;
        this.account_no_locked = account_no_locked;
        this.credential_no_expired = credential_no_expired;
    }

    public void generateUsername() {

        String nombre = persona.getNombre();
        String apellido = persona.getApellido();
        LocalDate fecha = persona.getNacimiento();

        if (nombre == null || apellido == null || fecha == null) {
            throw new IllegalArgumentException("Datos invalidos para generar username");
        }

        String username = nombre.substring(0, Math.min(4, nombre.length()))
                + apellido.substring(0, Math.min(2, apellido.length())) + fecha.getDayOfMonth();

        this.username = username.toLowerCase();
    }

    public static User create(Person persona, UserRole rol, String password){
        return new User(
            null, 
            persona, 
            null, 
            password, 
            UserRole.CLIENTE, 
            true, 
            true, 
            true, 
            true);
    }
}

