package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.infrastructure.entity.CustomerEntity;
import com.credito.creditcore.infrastructure.entity.LoanEntity;

public class LoanMapperOut {

    public static Loan toDomain(LoanEntity entity) {
        return new Loan(
                entity.getLoanId(),
                entity.getPrincipalAmount(),
                entity.getInterestRate(),
                entity.getTermInMonths(),
                entity.getLoanStatus(),
                entity.getRequestDate(),
                entity.getApprovalDate(),
                entity.getLoanType(),
                entity.getTotalInterest(),
                entity.getTotalAmountDue(),
                entity.getTotalPaid(),
                entity.getOutstandingBalance()
        );
    }

    public static LoanEntity toEntity(Loan loan, CustomerEntity customerEntity) {
        LoanEntity entity = new LoanEntity(
                customerEntity,
                loan.getPrincipalAmount(),
                loan.getInterestRate(),
                loan.getTermInMonths(),
                loan.getLoanStatus(),
                loan.getApplicationDate(),
                loan.getApprovalDate(),
                loan.getLoanType(),
                loan.getTotalInterest(),
                loan.getTotalAmountDue(),
                loan.getTotalPaid(),
                loan.getOutstandingBalance()
        );

        entity.setLoanId(loan.getLoanId());

        return entity;
    }

    public static LoanEntity updateEntity(LoanEntity entity, Loan loan) {
        entity.setRequestDate(loan.getApprovalDate());
        entity.setLoanStatus(loan.getLoanStatus());

        return entity;
    }
}