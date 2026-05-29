package com.credito.creditcore.infrastructure.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.PagoEntity;

public interface PagoRepositoryJpa extends JpaRepository<PagoEntity, Integer>{

}
