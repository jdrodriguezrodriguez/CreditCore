package com.credito.creditcore.application.usuario.port.in;

public interface ActualizarUsuarioUseCase {
    void actualizarUsuario(Integer idUser, String username, String password);
}
