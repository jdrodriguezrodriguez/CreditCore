package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.UpdateUsuarioDto;
import com.credito.creditcore.application.usuario.port.in.ActualizarUsuarioUseCase;
import com.credito.creditcore.application.usuario.port.in.ObtenerUsuarioUseCase;

@RestController
@RequestMapping("/api/credito/usuarios")
public class UsuarioController {

    private final ActualizarUsuarioUseCase actualizarUsuarioUseCase;
    private final ObtenerUsuarioUseCase obtenerUsuarioUseCase;

    public UsuarioController(ActualizarUsuarioUseCase actualizarUsuarioUseCase,
            ObtenerUsuarioUseCase obtenerUsuarioUseCase) {
        this.actualizarUsuarioUseCase = actualizarUsuarioUseCase;
        this.obtenerUsuarioUseCase = obtenerUsuarioUseCase;
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer idUser, @RequestBody UpdateUsuarioDto datos) {

        actualizarUsuarioUseCase.actualizarUsuario(idUser, datos.username(), datos.password());

        return ResponseEntity.ok(Map.of("Mensaje", "Usuario actualizado correctamente."));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Integer idUser) {

        return ResponseEntity.ok(obtenerUsuarioUseCase.buscarUsuario(idUser));
    }
}
