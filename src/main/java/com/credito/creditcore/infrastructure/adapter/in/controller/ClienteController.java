package com.credito.creditcore.infrastructure.adapter.in.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.ClienteDto;

@RestController
@RequestMapping("/api/credito/clientes")
public class ClienteController {
    
    @GetMapping("/{idCliente}")
    public ResponseEntity<?> buscarCliente(@PathVariable Integer idCliente){

        return ResponseEntity.ok(null);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer idCliente, @RequestBody ClienteDto clienteDto){

        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<?> registrarCliente(@RequestBody ClienteDto clienteDto){

        return ResponseEntity.ok(null);
    }

}
