package com.credito.creditcore.infrastructure.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.port.UserRepositoryPort;
import com.credito.creditcore.infrastructure.adapter.out.mapper.PersonMapperOut;
import com.credito.creditcore.infrastructure.adapter.out.mapper.UserMapperOut;
import com.credito.creditcore.infrastructure.entity.PersonEntity;
import com.credito.creditcore.infrastructure.entity.UserEntity;
import com.credito.creditcore.infrastructure.persistence.PersonRepositoryJpa;
import com.credito.creditcore.infrastructure.persistence.UserRepositoryJpa;

import jakarta.persistence.EntityNotFoundException;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepositoryJpa userRepositoryJpa;
    private final PersonRepositoryJpa personRepositoryJpa;

    public UserRepositoryAdapter(UserRepositoryJpa userRepositoryJpa,
            PersonRepositoryJpa personRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.personRepositoryJpa = personRepositoryJpa;
    }

    @Override
    public void save(User user) {

        PersonEntity personEntity = personRepositoryJpa.findById(user.getPerson().getPersonId())
                .orElseThrow(() -> new EntityNotFoundException());

        UserEntity userEntity = UserMapperOut.toEntity(user, personEntity);

        userRepositoryJpa.save(userEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return userRepositoryJpa.findByUsername(username).map(
                t -> {
                    return UserMapperOut.toDomain(t);
                });
    }

    @Override
    public void update(Integer userId, String username, String password) {
        UserEntity userEntity = userRepositoryJpa.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException());

        userEntity.setUsername(username);
        userEntity.setPassword(password);

        userRepositoryJpa.save(userEntity);
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepositoryJpa.findById(userId).map(
            userEntity ->{
                Person person = PersonMapperOut.toDomain(userEntity.getPerson());
                return UserMapperOut.toDomain(userEntity, person);
            }
        );
    }
}
