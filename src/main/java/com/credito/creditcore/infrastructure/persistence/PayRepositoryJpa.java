package com.credito.creditcore.infrastructure.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.PaymentEntity;

public interface PayRepositoryJpa extends JpaRepository<PaymentEntity, Integer>{

}
