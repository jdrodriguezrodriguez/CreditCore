package com.credito.creditcore.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.ClienteEntity;

public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Integer>{
    
}
