package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;

public class LoanMapperOut {

    public static Loan toDomain(PrestamoEntity entity) {
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

    public static PrestamoEntity toEntity(Loan loan, ClienteEntity cliente) {
        PrestamoEntity entity = new PrestamoEntity(
                cliente,
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

    public static PrestamoEntity updateEntity(PrestamoEntity entity, Loan loan) {
        entity.setFechaAprobacion(loan.getApprovalDate());
        entity.setEstadoPrestamo(loan.getLoanStatus());

        return entity;
    }
}