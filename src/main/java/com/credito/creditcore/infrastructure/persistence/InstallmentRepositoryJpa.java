package com.credito.creditcore.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.InstallmentEntity;

public interface InstallmentRepositoryJpa extends JpaRepository<InstallmentEntity, Integer>{
    List<InstallmentEntity> findByPrestamo_Cliente_IdCliente(Integer customerId);
}
