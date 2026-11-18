package com.credito.creditcore.infrastructure.persistence;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.PaymentEntity;

public interface PayRepositoryJpa extends JpaRepository<PaymentEntity, Integer>{

    List<PaymentEntity> findByInstallmentEntity_Loan_LoanId(Integer loanId);
}   
