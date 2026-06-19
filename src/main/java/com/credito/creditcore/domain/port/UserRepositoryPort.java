package com.credito.creditcore.domain.port;

import java.util.Optional;

import com.credito.creditcore.domain.model.User;

public interface UserRepositoryPort {

    void save(User usuario);
    void update(Integer idUser, String username, String password);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer idUser);
} 
