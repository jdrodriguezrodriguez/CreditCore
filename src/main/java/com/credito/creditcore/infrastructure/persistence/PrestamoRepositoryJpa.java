package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.PrestamoEntity;

public interface PrestamoRepositoryJpa extends JpaRepository<PrestamoEntity, Integer>{
    Optional<PrestamoEntity> findByCliente_Persona_idPersona(Integer idPersona);
}
