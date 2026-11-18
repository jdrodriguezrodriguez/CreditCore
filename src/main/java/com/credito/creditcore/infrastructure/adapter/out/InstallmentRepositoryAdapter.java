package com.credito.creditcore.infrastructure.adapter.out;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.CustomerMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.InstallmentMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.LoanMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonMapperOut;
import com.credito.creditcore.infrastructure.entity.CustomerEntity;
import com.credito.creditcore.infrastructure.entity.InstallmentEntity;
import com.credito.creditcore.infrastructure.entity.LoanEntity;
import com.credito.creditcore.infrastructure.entity.PersonEntity;
import com.credito.creditcore.infrastructure.persistence.InstallmentRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class InstallmentRepositoryAdapter implements InstallmentRepositoryPort {

    private final InstallmentRepositoryJpa repositoryJpa;

    public InstallmentRepositoryAdapter(InstallmentRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public void saveInstallments(List<Installment> installments, Loan loan, Customer customer) {

        PersonEntity personaEntity = PersonMapperOut.toEntity(customer.getPerson());
        CustomerEntity customerEntity = CustomerMapperOut.toEntity(customer, personaEntity);

        LoanEntity loanEntity = LoanMapperOut.toEntity(loan, customerEntity);

        List<InstallmentEntity> entities = InstallmentMapperOut.toEntityList(installments, loanEntity);

        repositoryJpa.saveAll(entities);
    }

    @Override
    public Optional<Installment> findById(Integer installmentId) {
        return repositoryJpa.findById(installmentId)
                .map(InstallmentMapperOut::toDomain);
    }

    @Override
    public void updateInstallment(Installment installment) {

        InstallmentEntity entity = repositoryJpa.findById(installment.getInstallmentId())
                .orElseThrow(() -> new EntityNotFoundException("Installment not found"));

        repositoryJpa.save(
                InstallmentMapperOut.updatePaidAmount(entity, installment));
    }

    @Override
    public List<Installment> findByLoanId(Integer loanId) {

        List<InstallmentEntity> entities = repositoryJpa.findByLoan_LoanId(loanId);

        return InstallmentMapperOut.toDomainList(entities);
    }

    @Override
    public Optional<Installment> findByLoanIdAndNumber(Integer loanId, Integer installmentNumber) {
        return repositoryJpa.findByLoan_LoanIdAndInstallmentNumber(loanId, installmentNumber)
                .map(InstallmentMapperOut::toDomain);
    }
}