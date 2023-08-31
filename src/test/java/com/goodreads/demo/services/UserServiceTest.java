package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.entities.User;
import com.goodreads.demo.enums.Role;
import com.goodreads.demo.exceptions.IdenticalEmailException;
import com.goodreads.demo.exceptions.UserAlreadyExistsException;
import com.goodreads.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    User existingUser;
    User newUser;

    @BeforeEach
    void setUp() {
        existingUser = User.builder()
            .id(1)
            .role(Role.READER)
            .email("existinguser@email.com")
            .password(passwordEncoder.encode("321"))
            .firstName("firstName2")
            .lastName("lastname2")
            .build();

        newUser = User.builder()
            .id(2)
            .email("newuser@email.com")
            .password(passwordEncoder.encode("123"))
            .role(Role.AUTHOR)
            .firstName("firstName")
            .lastName("lastname")
            .build();
    }

    @Test
    void createUserExistingUserTest() {
        RegisterRequest registerRequestExistingUser = RegisterRequest.builder()
            .email("exisTingUser@email.com")
            .password("123")
            .role(Role.AUTHOR)
            .firstName("firstName")
            .lastName("lastname")
            .build();

        when(userRepository.findByEmail(registerRequestExistingUser.email().toLowerCase())).thenReturn(Optional.of(existingUser));
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(registerRequestExistingUser));
    }

    @Test
    void createUserTest() {
        RegisterRequest registerRequest = RegisterRequest.builder()
            .email("NEWUseR@email.com")
            .password("123")
            .role(Role.AUTHOR)
            .firstName("firstName")
            .lastName("lastname")
            .build();

        when(userRepository.findByEmail(registerRequest.email().toLowerCase())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        var result = userService.createUser(registerRequest);

        assertNotNull(result);
        assertEquals(newUser.getEmail(), result.email());
        assertEquals(newUser.getRole(), result.role());
        assertTrue(passwordEncoder.matches(registerRequest.password(), newUser.getPassword()));
        assertEquals(newUser.getPassword(), result.password());
        assertEquals(newUser.getFirstName(), result.firstName());
        assertEquals(newUser.getLastName(), result.lastName());
    }

    @Test
    void changeEmailTestIdenticalEmailException() {
        String currentEmail = existingUser.getEmail();
        String newEmail = "exisTingUser@emAiL.cOm";
        assertThrows(IdenticalEmailException.class, () -> userService.changeEmail(currentEmail, newEmail));
    }

    @Test
    void changeEmailTestUserAlreadyExistsException() {
        String currentEmail = existingUser.getEmail();
        String newEmail = newUser.getEmail().toUpperCase();

        when(userRepository.findByEmail(currentEmail.toLowerCase())).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(newEmail.toLowerCase())).thenReturn(Optional.of(newUser));

        assertThrows(UserAlreadyExistsException.class, () -> userService.changeEmail(currentEmail, newEmail));
    }

    @Test
    void changeEmailTestUsernameNotFoundException() {
        String currentEmail = "usernameNotFound@email.com";
        String newEmail = "newEamil@email.com";

        when(userRepository.findByEmail(currentEmail.toLowerCase())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.changeEmail(currentEmail, newEmail));
    }

    @Test
    void changeEmailTest() {
        String currentEmail = existingUser.getEmail();
        String newEmail = "newEmail@email.com".toUpperCase();

        User existingUserUpdated = User.builder()
            .id(existingUser.getId())
            .email(newEmail.toLowerCase())
            .password(existingUser.getPassword())
            .role(existingUser.getRole())
            .firstName(existingUser.getFirstName())
            .lastName(existingUser.getLastName())
            .build();

        when(userRepository.findByEmail(currentEmail.toLowerCase())).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(newEmail.toLowerCase())).thenReturn(Optional.empty());
        when(userRepository.save(existingUser)).thenReturn(existingUserUpdated);

        userService.changeEmail(currentEmail, newEmail);

        assertTrue(passwordEncoder.matches("321", existingUserUpdated.getPassword()));
        assertEquals(existingUserUpdated.getEmail(), existingUser.getEmail());
        assertEquals(existingUserUpdated.getRole(), existingUser.getRole());
        assertEquals(existingUserUpdated.getFirstName(), existingUser.getFirstName());
        assertEquals(existingUserUpdated.getLastName(), existingUser.getLastName());
        assertEquals(existingUserUpdated.getPassword(), existingUser.getPassword());
    }
}