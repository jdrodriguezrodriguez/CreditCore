package com.credito.creditcore.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.UsuarioEntity;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    
}
