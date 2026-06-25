package com.credito.creditcore.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credito.creditcore.infrastructure.entity.UserEntity;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Integer>{
    Optional<UserEntity> findByUsername(String username);
}
