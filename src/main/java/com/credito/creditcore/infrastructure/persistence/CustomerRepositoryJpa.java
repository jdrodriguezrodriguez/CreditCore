package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.CustomerEntity;

public interface CustomerRepositoryJpa extends JpaRepository<CustomerEntity, Integer>{
    Optional<CustomerEntity> findByPerson_PersonId(Integer personId);
}
