package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Loan;
import com.credito.creditcore.domain.port.LoanRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.LoanMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;
import com.credito.creditcore.infrastructure.persistence.PrestamoRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class LoanRepositoryAdapter implements LoanRepositoryPort {

    private final PrestamoRepositoryJpa repositoryJpa;

    public LoanRepositoryAdapter(PrestamoRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Optional<Loan> findByCustomerId(Integer idCliente) {
        return repositoryJpa.findByCliente_IdCliente(idCliente)
                .map(p -> {
                    return LoanMapperOut.toDomain(p);
                });
    }

    @Override
    public void save(Loan prestamo, Customer cliente) {
        PersonaEntity personaEntity = PersonaMapperOut.toEntity(cliente.getPerson());
        ClienteEntity clienteEntity = ClienteMapperOut.toEntity(cliente, personaEntity);
        PrestamoEntity prestamoEntity = LoanMapperOut.toEntity(prestamo, clienteEntity);

        repositoryJpa.save(prestamoEntity);
    }

    @Override
    public void update(Loan loan) {
        PrestamoEntity prestamoEntity = repositoryJpa.findById(loan.getLoanId()).orElseThrow(
                () -> new EntityNotFoundException());
        prestamoEntity = LoanMapperOut.updateEntity(prestamoEntity, loan);

        repositoryJpa.save(prestamoEntity);
    }

    @Override
    public Optional<Loan> findByLoanId(Integer idPrestamo) {
        return repositoryJpa.findById(idPrestamo).map(p -> {
            return LoanMapperOut.toDomain(p);
        });
    }
}
