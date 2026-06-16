package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.port.PrestamorepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.ClienteMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonaMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PrestamoMapperOut;
import com.credito.creditcore.infrastructure.entity.ClienteEntity;
import com.credito.creditcore.infrastructure.entity.PersonaEntity;
import com.credito.creditcore.infrastructure.entity.PrestamoEntity;
import com.credito.creditcore.infrastructure.persistence.PrestamoRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class PrestamoRepositoryAdapter implements PrestamorepositoryPort {

    private final PrestamoRepositoryJpa repositoryJpa;

    public PrestamoRepositoryAdapter(PrestamoRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Optional<Prestamo> obtenerPorIdCliente(Integer idCliente) {
        return repositoryJpa.findByCliente_IdCliente(idCliente)
                .map(p -> {
                    return PrestamoMapperOut.toDomain(p);
                });
    }

    @Override
    public void guardar(Prestamo prestamo, Customer cliente) {
        PersonaEntity personaEntity = PersonaMapperOut.toEntity(cliente.getPersona());
        ClienteEntity clienteEntity = ClienteMapperOut.toEntity(cliente, personaEntity);
        PrestamoEntity prestamoEntity = PrestamoMapperOut.crearEntidad(prestamo, clienteEntity);

        repositoryJpa.save(prestamoEntity);
    }

    @Override
    public void actualizar(Integer idCliente, Prestamo prestamo) {
        PrestamoEntity prestamoEntity = repositoryJpa.findByCliente_IdCliente(idCliente).orElseThrow(
                () -> new EntityNotFoundException());
        prestamoEntity = PrestamoMapperOut.actualizarEntidad(prestamoEntity, prestamo);

        repositoryJpa.save(prestamoEntity);
    }

    @Override
    public Optional<Prestamo> obtenerPorId(Integer idPrestamo) {
        return repositoryJpa.findById(idPrestamo).map(p -> {
            return PrestamoMapperOut.toDomain(p);
        });
    }
}
