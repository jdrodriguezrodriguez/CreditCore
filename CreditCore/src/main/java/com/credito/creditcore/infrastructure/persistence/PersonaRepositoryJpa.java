package com.credito.creditcore.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.PersonaEntity;

public interface PersonaRepositoryJpa extends JpaRepository<PersonaEntity, Integer>{

} 
