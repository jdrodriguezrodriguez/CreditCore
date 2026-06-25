package com.credito.creditcore.infrastructure.adapter.in.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credito.creditcore.application.dto.user.UpdateUserDto;
import com.credito.creditcore.application.user.port.in.GetUserUseCase;
import com.credito.creditcore.application.user.port.in.UpdateUserUseCase;

@RestController
@RequestMapping("/api/credito/users")
public class UserController {

    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserController(UpdateUserUseCase updateUserUseCase,
            GetUserUseCase getUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody UpdateUserDto request) {

        updateUserUseCase.updateUser(userId, request.username(), request.password());

        return ResponseEntity.ok(Map.of("message", "User updated successfully."));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId) {

        return ResponseEntity.ok(getUserUseCase.getUser(userId));
    }
}
