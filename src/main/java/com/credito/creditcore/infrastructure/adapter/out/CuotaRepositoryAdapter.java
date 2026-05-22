package com.credito.creditcore.infrastructure.adapter.out;

import java.util.List;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Cuota;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.port.CuotaRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.CuotaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PrestamoMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.CuotaEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;
import com.credito.creditcore.infrastructure.persistence.CuotaRepositoryJpa;

@Component
public class CuotaRepositoryAdapter implements CuotaRepositoryPort {

    private CuotaRepositoryJpa repositoryJpa;

    public CuotaRepositoryAdapter(CuotaRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public void guardar(List<Cuota> cuotas, Prestamo prestamo, Cliente cliente) {

        PersonaEntity personaEntity = PersonaMapperOut.toEntity(cliente.getPersona());
        ClienteEntity clienteEntity = ClienteMapperOut.toEntity(prestamo.getCliente(), personaEntity);
        PrestamoEntity prestamoEntity = PrestamoMapperOut.toEntity(prestamo, clienteEntity);

        List<CuotaEntity> cuotaEntities = CuotaMapperOut.crearEntidad(cuotas, prestamoEntity);

        repositoryJpa.saveAll(cuotaEntities);
    }

}
