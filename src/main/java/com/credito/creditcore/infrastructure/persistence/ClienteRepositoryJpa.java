package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.ClienteEntity;

public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Integer>{
    Optional<ClienteEntity> findByPersona_idPersona(Integer idPersona);
}
