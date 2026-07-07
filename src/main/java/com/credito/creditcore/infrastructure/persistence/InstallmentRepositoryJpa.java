package com.credito.creditcore.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.InstallmentEntity;

public interface InstallmentRepositoryJpa extends JpaRepository<InstallmentEntity, Integer>{
    List<InstallmentEntity> findByLoan_Customer_CustomerId(Integer customerId);

    Optional<InstallmentEntity> findByLoan_Customer_CustomerIdAndInstallmentNumber(Integer loanId, Integer number);
}
