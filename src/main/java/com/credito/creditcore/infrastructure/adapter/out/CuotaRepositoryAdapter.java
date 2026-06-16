package com.credito.creditcore.infrastructure.adapter.out;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Installment;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.port.InstallmentRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.CuotaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PrestamoMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.CuotaEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;
import com.credito.creditcore.infrastructure.persistence.CuotaRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CuotaRepositoryAdapter implements InstallmentRepositoryPort {

    private CuotaRepositoryJpa repositoryJpa;

    public CuotaRepositoryAdapter(CuotaRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public void guardarCuotas(List<Installment> cuotas, Prestamo prestamo, Customer cliente) {

        PersonaEntity personaEntity = PersonaMapperOut.toEntity(cliente.getPersona());
        ClienteEntity clienteEntity = ClienteMapperOut.toEntity(prestamo.getCliente(), personaEntity);
        PrestamoEntity prestamoEntity = PrestamoMapperOut.toEntity(prestamo, clienteEntity);

        List<CuotaEntity> cuotaEntities = CuotaMapperOut.crearEntidad(cuotas, prestamoEntity);

        repositoryJpa.saveAll(cuotaEntities);
    }

    @Override
    public Optional<Installment> findById(Integer idCuota) {
        return repositoryJpa.findById(idCuota).map(
                CuotaMapperOut::toDomain);
    }

    @Override
    public void updateInstallment(Integer idCuota, BigDecimal monto) {
        CuotaEntity cuotaEntity = repositoryJpa.findById(idCuota)
                .orElseThrow(() -> new EntityNotFoundException());

        repositoryJpa.save(CuotaMapperOut.updateEntity(cuotaEntity, monto));
    }

    @Override
    public List<Installment> findByLoanId(Integer idCliente) {
        List<CuotaEntity> cuotaEntity = repositoryJpa.findByPrestamo_Cliente_IdCliente(idCliente);
        
        return CuotaMapperOut.cuotasToDomain(cuotaEntity);
    }

}
