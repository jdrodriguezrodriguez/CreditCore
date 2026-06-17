package com.credito.creditcore.infrastructure.adapter.out;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.CuotaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.LoanMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.CuotaEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.persistence.CuotaRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class InstallmentRepositoryAdapter implements InstallmentRepositoryPort {

    private final CuotaRepositoryJpa repositoryJpa;

    public InstallmentRepositoryAdapter(CuotaRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public void saveInstallments(List<Installment> installments, Loan loan, Customer customer) {

        PersonaEntity personaEntity = PersonaMapperOut.toEntity(customer.getPerson());
        ClienteEntity customerEntity = ClienteMapperOut.toEntity(customer, personaEntity);
        var loanEntity = LoanMapperOut.toEntity(loan, customerEntity);

        List<CuotaEntity> entities =
                CuotaMapperOut.toEntityList(installments, loanEntity);

        repositoryJpa.saveAll(entities);
    }

    @Override
    public Optional<Installment> findById(Integer installmentId) {
        return repositoryJpa.findById(installmentId)
                .map(CuotaMapperOut::toDomain);
    }

    @Override
    public void updateInstallment(Integer installmentId, BigDecimal paidAmount) {

        CuotaEntity entity = repositoryJpa.findById(installmentId)
                .orElseThrow(() -> new EntityNotFoundException("Installment not found"));

        repositoryJpa.save(
                CuotaMapperOut.updateEntity(entity, paidAmount)
        );
    }

    @Override
    public List<Installment> findByCustomerId(Integer customerId) {

        List<CuotaEntity> entities =
                repositoryJpa.findByPrestamo_Cliente_IdCliente(customerId);

        return CuotaMapperOut.toDomainList(entities);
    }
}