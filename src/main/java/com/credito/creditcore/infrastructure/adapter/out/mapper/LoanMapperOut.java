package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.infrastructure.entity.CustomerEntity;
import com.credito.creditcore.infrastructure.entity.LoanEntity;

public class LoanMapperOut {

    public static Loan toDomain(LoanEntity entity) {
        return new Loan(
                entity.getIdPrestamo(),
                entity.getMonto(),
                entity.getInteres(),
                entity.getPlazo(),
                entity.getEstadoPrestamo(),
                entity.getFechaSolicitud(),
                entity.getFechaAprobacion(),
                entity.getTipoPrestamo(),
                entity.getInteresTotal(),
                entity.getTotalPagar(),
                entity.getTotalPagado(),
                entity.getSaldoPendiente()
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

        entity.setIdPrestamo(loan.getLoanId());

        return entity;
    }

    public static LoanEntity updateEntity(LoanEntity entity, Loan loan) {
        entity.setFechaAprobacion(loan.getApprovalDate());
        entity.setEstadoPrestamo(loan.getLoanStatus());

        return entity;
    }
}