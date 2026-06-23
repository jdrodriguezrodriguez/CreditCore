package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.usuario.UpdateUsuarioDto;
import com.credito.creditcore.application.usuario.port.in.UpdateUserUseCase;
import com.credito.creditcore.application.usuario.port.in.GetUserUseCase;

@RestController
@RequestMapping("/api/credito/usuarios")
public class UsuarioController {

    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserUseCase getUserUseCase;

    public UsuarioController(UpdateUserUseCase updateUserUseCase,
            GetUserUseCase getUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer idUser, @RequestBody UpdateUsuarioDto datos) {

        updateUserUseCase.updateUser(idUser, datos.username(), datos.password());

        return ResponseEntity.ok(Map.of("Mensaje", "Usuario actualizado correctamente."));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Integer idUser) {

        return ResponseEntity.ok(getUserUseCase.getUser(idUser));
    }
}
