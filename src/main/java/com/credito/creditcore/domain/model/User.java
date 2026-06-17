package com.credito.creditcore.domain.model;

import java.time.LocalDate;

import com.credito.creditcore.domain.model.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private Integer userId;
    private Person person;

    private String username;
    private String password;

    private UserRole userRole;

    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public User() {
    }

    public User(
            Integer userId, Person person, String username, String password,
            UserRole userRole, boolean enabled, boolean accountNonExpired,
            boolean accountNonLocked, boolean credentialsNonExpired) {

        if (person == null) {
            throw new IllegalArgumentException("Person is required.");
        }

        if (password == null) {
            throw new IllegalArgumentException("Password is required.");
        }

        this.userId = userId;
        this.person = person;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void generateUsername() {

        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        LocalDate birthDate = person.getBirthDate();

        if (firstName == null || lastName == null || birthDate == null) {
            throw new IllegalArgumentException("Invalid data for username generation.");
        }

        String generatedUsername = firstName.substring(0, Math.min(4, firstName.length()))
                + lastName.substring(0, Math.min(2, lastName.length()))
                + birthDate.getDayOfMonth();

        this.username = generatedUsername.toLowerCase();
    }

    public static User create(Person person, UserRole role, String password) {
        return new User(
                null,
                person,
                null,
                password,
                role,
                true,
                true,
                true,
                true);
    }
}
