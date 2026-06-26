package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.PersonEntity;

public interface PersonRepositoryJpa extends JpaRepository<PersonEntity, Integer>{
    Optional<PersonEntity> findByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);
} 
