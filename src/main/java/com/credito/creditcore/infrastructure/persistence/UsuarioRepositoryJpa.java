package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.UsuarioEntity;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Integer>{
    Optional<UsuarioEntity> findByUsername(String username);
}
