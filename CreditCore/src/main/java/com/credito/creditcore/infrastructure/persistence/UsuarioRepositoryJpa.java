package com.credito.creditcore.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.UsuarioEntity;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, Integer>{
    
}
