package com.credito.creditcore.application.prestamo.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.credito.creditcore.application.dto.prestamo.CrearPrestamoRequestDto;
import com.credito.creditcore.application.prestamo.port.CrearPrestamoUseCase;
import com.credito.creditcore.domain.model.Customer;
import com.credito.creditcore.domain.model.Prestamo;
import com.credito.creditcore.domain.model.enums.EstadoPrestamo;
import com.credito.creditcore.domain.port.CustomerRepositoryPort;
import com.credito.creditcore.domain.port.LoanRepositoryPort;

@Service
public class CrearPrestamoService implements CrearPrestamoUseCase {

    private CustomerRepositoryPort clienteRepositoryPort;
    private LoanRepositoryPort prestamorepositoryPort;

    public CrearPrestamoService(CustomerRepositoryPort clienteRepositoryPort,
            LoanRepositoryPort prestamorepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.prestamorepositoryPort = prestamorepositoryPort;
    }

    @Override
    public Prestamo crearPrestamo(CrearPrestamoRequestDto datos, Integer idPersona) {

        Customer cliente = clienteRepositoryPort.obtenerPorIdPersona(idPersona).orElseThrow(
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
