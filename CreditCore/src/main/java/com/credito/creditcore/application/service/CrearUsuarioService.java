package com.credito.creditcore.application.service;

import com.credito.creditcore.application.port.in.CrearUsuarioUseCase;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;

public class CrearUsuarioService implements CrearUsuarioUseCase{

    private UsuarioRepositoryPort usuarioRepositoryPort;
    public CrearUsuarioService(UsuarioRepositoryPort usuarioRepositoryPort){
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario crearUsuario(String nombre, String email) {
        
        Usuario usuario = new Usuario(null, null, nombre, email, null, false, false, false, false);

        return usuarioRepositoryPort.guardar(usuario);
    }
    
}
