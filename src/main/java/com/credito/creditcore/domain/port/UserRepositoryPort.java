package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.User;

public interface UserRepositoryPort {

    void save(User usuario);
    void actualizar(Integer idUser, String username, String password);
    Optional<User> buscarPorUsername(String username);
    Optional<User> consultar(Integer idUser);
} 
