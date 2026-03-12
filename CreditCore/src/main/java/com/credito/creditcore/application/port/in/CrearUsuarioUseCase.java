package com.credito.creditcore.application.port.in;

import com.credito.creditcore.domain.model.Usuario;

public interface CrearUsuarioUseCase {
    Usuario crearUsuario(String nombre, String email);
}
