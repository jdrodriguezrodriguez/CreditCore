package com.credito.creditcore.application.usuario.service;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.usuario.port.in.ObtenerUsuarioUseCase;
import com.credito.creditcore.domain.model.Usuario;
import com.credito.creditcore.domain.port.UsuarioRepositoryPort;

@Service
public class ObtenerUsuarioService implements ObtenerUsuarioUseCase{

    private UsuarioRepositoryPort usuarioRepositoryPort;
    public ObtenerUsuarioService(UsuarioRepositoryPort usuarioRepositoryPort){
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario buscarUsuario(Integer idUser) {

        return usuarioRepositoryPort.consultar(idUser);
    }
    
}
