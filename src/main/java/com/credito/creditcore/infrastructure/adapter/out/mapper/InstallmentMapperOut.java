package com.credito.creditcore.infrastructure.adapter.out.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.infrastructure.entity.InstallmentEntity;
import com.credito.creditcore.infrastructure.entity.LoanEntity;

public class InstallmentMapperOut {
    public static List<InstallmentEntity> toEntityList(List<Installment> installments, LoanEntity loanEntity) {
        List<InstallmentEntity> installmentEntities = new ArrayList<>();

        for (Installment installment : installments) {
            InstallmentEntity installmentEntity = new InstallmentEntity(
                    loanEntity,
                    installment.getInstallmentNumber(),
                    installment.getInstallmentAmount(),
                    installment.getDueDate(),
                    installment.getStatus(),
                    installment.getInitialBalance(),
                    installment.getInterest(),
                    installment.getCapitalAmortization(),
                    installment.getFinalBalance(),
                    installment.getPaidAmount(),
                    installment.getLateFee(),
                    installment.getActualPaymentDate());

            installmentEntities.add(installmentEntity);
        }

        return installmentEntities;
    }

    public static List<Installment> toDomainList(List<InstallmentEntity> installmentEntities) {
        List<Installment> installments = new ArrayList<>();

        for (InstallmentEntity installmentEntity : installmentEntities) {
            Installment installment = new Installment(
                    installmentEntity.getInstallmentNumber(),
                    installmentEntity.getDueDate(),
                    installmentEntity.getStatus(),
                    installmentEntity.getInitialBalance(),
                    installmentEntity.getInterest(),
                    installmentEntity.getCapitalAmortization(),
                    installmentEntity.getInstallmentAmount(),
                    installmentEntity.getFinalBalance(),
                    installmentEntity.getPaidAmount(),
                    installmentEntity.getLateFee(),
                    installmentEntity.getActualPaymentDate());

            installments.add(installment);
        }

        return installments;
    }

    public static Installment toDomain(InstallmentEntity installmentEntity) {
        return new Installment(
                installmentEntity.getInstallmentId(),
                null,
                installmentEntity.getInstallmentNumber(),
                installmentEntity.getDueDate(),
                installmentEntity.getStatus(),
                installmentEntity.getInitialBalance(),
                installmentEntity.getInterest(),
                installmentEntity.getCapitalAmortization(),
                installmentEntity.getInstallmentAmount(),
                installmentEntity.getFinalBalance(),
                installmentEntity.getPaidAmount(),
                installmentEntity.getLateFee(),
                installmentEntity.getActualPaymentDate());
    }

    public static InstallmentEntity updatePaidAmount(InstallmentEntity installmentEntity, BigDecimal amount) {
        installmentEntity.setPaidAmount(amount);

        return installmentEntity;
    }

    public static InstallmentEntity toEntity(Installment installment) {
        InstallmentEntity installmentEntity = new InstallmentEntity(
                null,
                installment.getInstallmentNumber(),
                installment.getInstallmentAmount(),
                installment.getDueDate(),
                installment.getStatus(),
                installment.getInitialBalance(),
                installment.getInterest(),
                installment.getCapitalAmortization(),
                installment.getFinalBalance(),
                installment.getPaidAmount(),
                installment.getLateFee(),
                installment.getActualPaymentDate());

        installmentEntity.setInstallmentId(installment.getInstallmentId());

        return installmentEntity;
    }
}
