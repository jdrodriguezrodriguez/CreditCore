package com.credito.creditcore.application.dto.person;

import com.credito.creditcore.application.dto.user.UserDto;

public record RegisterPersonCommand(
                PersonDto person,
                UserDto user) {
}
