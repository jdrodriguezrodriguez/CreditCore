package com.credito.creditcore.infrastructure.adapter.out.mapper;

import com.credito.creditcore.application.dto.user.UserDto;
import com.credito.creditcore.domain.model.Person;
import com.credito.creditcore.domain.model.User;
import com.credito.creditcore.domain.model.enums.UserRole;
import com.credito.creditcore.infrastructure.entity.PersonEntity;
import com.credito.creditcore.infrastructure.entity.UserEntity;

public class UserMapperOut {

    public static UserEntity toEntity(User user, PersonEntity personEntity) {
        return new UserEntity(
                personEntity,
                user.getUsername(),
                user.getPassword(),
                user.getUserRole(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired()
        );
    }

    public static User toDomain(UserEntity entity) {
    return new User(
            entity.getUserId(),
            entity.getUsername(),
            entity.getPassword(),
            entity.getRole(),
            entity.isEnabled(),
            entity.isAccountNonExpired(),
            entity.isAccountNonLocked(),
            entity.isCredentialsNonExpired());
}

    public static User toDomain(UserEntity entity, Person person) {
        return new User(
                entity.getUserId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRole(),
                entity.isEnabled(),
                entity.isAccountNonExpired(),
                entity.isAccountNonLocked(),
                entity.isCredentialsNonExpired()
        );
    }

    public static User fromDto(UserDto dto) {
        User user = new User();
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(dto.password());

        return user;
    }
}