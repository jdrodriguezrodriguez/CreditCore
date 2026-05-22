package com.credito.creditcore.application.prestamo.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.prestamo.CrearPrestamoRequestDto;
import com.credito.creditcore.application.prestamo.port.CrearPrestamoUseCase;
import com.credito.creditcore.domain.model.Cliente;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.model.enums.EstadoPrestamo;
import com.credito.creditcore.domain.port.ClienteRepositoryPort;
import com.credito.creditcore.domain.port.PrestamorepositoryPort;

@Service
public class CrearPrestamoService implements CrearPrestamoUseCase {

    private ClienteRepositoryPort clienteRepositoryPort;
    private PrestamorepositoryPort prestamorepositoryPort;

    public CrearPrestamoService(ClienteRepositoryPort clienteRepositoryPort,
            PrestamorepositoryPort prestamorepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.prestamorepositoryPort = prestamorepositoryPort;
    }

    @Override
    public Prestamo crearPrestamo(CrearPrestamoRequestDto datos, Integer idPersona) {

        Cliente cliente = clienteRepositoryPort.obtenerPorIdPersona(idPersona).orElseThrow(
                () -> new IllegalArgumentException("No se encontro un cliente con el idPersona: " + idPersona));

        Prestamo prestamo = Prestamo.crear(
                cliente,
                datos.monto(), 
                datos.tasaInteres(), 
                datos.plazo(), 
                EstadoPrestamo.SOLICITADO, 
                LocalDate.now(), 
                null,
                datos.tipoPrestamo(),
                datos.interesTotal(),
                datos.totalPagar(),
                BigDecimal.ZERO,
                datos.monto()
        );

        if (cliente != null) {
            prestamorepositoryPort.guardar(prestamo, cliente);
        }
        
        return prestamo;
    }

}
