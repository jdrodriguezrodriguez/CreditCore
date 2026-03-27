package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.Usuario;

public interface UsuarioRepositoryPort {

    void guardar(Usuario usuario);
    void actualizar(Integer idUser, String username, String password);
    Optional<Usuario> buscarPorUsername(String username);
    Usuario consultar(Integer idUser);
} 
