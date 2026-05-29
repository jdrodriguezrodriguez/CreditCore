package com.credito.creditcore.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.CuotaEntity;

public interface CuotaRepositoryJpa extends JpaRepository<CuotaEntity, Integer>{
    List<CuotaEntity> findByPrestamo_Cliente_IdCliente(Integer idCliente);
}
