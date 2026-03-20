package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public interface PersonaRepositoryJpa extends JpaRepository<PersonaEntity, Integer>{
    Optional<PersonaEntity> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
} 
