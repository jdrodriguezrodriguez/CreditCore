package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.LoanEntity;

public interface LoanRepositoryJpa extends JpaRepository<LoanEntity, Integer>{
    Optional<LoanEntity> findByCliente_IdCliente(Integer customerId);
}
