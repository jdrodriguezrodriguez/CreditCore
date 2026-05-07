package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.port.PrestamorepositoryPort;
import com.credito.creditcore.infrastructure.persistence.PrestamoRepositoryJpa;

public class PrestamoRepositoryAdapter implements PrestamorepositoryPort{

    private PrestamoRepositoryJpa repositoryJpa;

    public PrestamoRepositoryAdapter(PrestamoRepositoryJpa repositoryJpa){
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public Optional<Prestamo> obtenerPorIdPersona(Integer idPersona) {
        return repositoryJpa.findByCliente_Persona_idPersona(idPersona)
            .map(null);
    }
    
}
