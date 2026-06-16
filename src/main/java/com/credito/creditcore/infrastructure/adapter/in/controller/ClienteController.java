package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.customer.port.GetCustomerUseCase;
import com.credito.creditcore.application.customer.port.RegisterCustomerUseCase;
import com.credito.creditcore.application.dto.cliente.ClienteDto;
import com.credito.creditcore.application.dto.cliente.SalarioClienteDto;
import com.credito.creditcore.infrastructure.adapter.in.mapper.ClienteMapperIn;

@RestController
@RequestMapping("/api/credito/clientes")
public class ClienteController {

    private final RegisterCustomerUseCase registrarClienteUseCase;
    private final GetCustomerUseCase getCustomerUseCase;

    public ClienteController(RegisterCustomerUseCase registrarClienteUseCase, GetCustomerUseCase getCustomerUseCase) {
        this.registrarClienteUseCase = registrarClienteUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<?> buscarCliente(@PathVariable Integer idCliente) {
        ClienteDto clienteDto = ClienteMapperIn.crearDto(getCustomerUseCase.getCustomer(idCliente));
        
        return ResponseEntity.ok(clienteDto);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer idCliente, @RequestBody SalarioClienteDto clienteDto) {

        return ResponseEntity.ok(null);
    }

    @PostMapping("/{idPersona}")
    public ResponseEntity<?> registrarCliente(@PathVariable Integer idPersona, @RequestBody SalarioClienteDto clienteDto) {

        registrarClienteUseCase.registerCustomer(clienteDto.salario(), idPersona);

        return ResponseEntity.ok(Map.of("Mensaje", "Se registro el cliente correctamente."));
    }

}
