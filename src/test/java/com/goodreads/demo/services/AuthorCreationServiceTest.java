package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.entities.users.Author;
import com.goodreads.demo.enums.Role;
import com.goodreads.demo.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig
class AuthorCreationServiceTest {

    @Autowired
    private AuthorCreationService authorCreationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthorRepository authorRepository;

    RegisterRequest validRegisterRequest;
    Author author;

    @BeforeEach
    void setUp() {
        validRegisterRequest = new RegisterRequest("email@domain.com", "123", Role.ADMINISTRATOR, "firstName", "lastName");
        author = new Author(1, validRegisterRequest.email(), passwordEncoder.encode(validRegisterRequest.password()),
            validRegisterRequest.firstName(), validRegisterRequest.lastName());
    }

    @Test
    void createValidUserTest() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        var result = authorCreationService.createUser(validRegisterRequest);
        verify(authorRepository).save(any(Author.class));

        assertNotNull(result);
        assertTrue(passwordEncoder.matches(validRegisterRequest.password(), result.getPassword()));
        assertEquals(result.getId(), author.getId());
        assertEquals(result.getEmail(), author.getEmail());
        assertEquals(result.getPassword(), author.getPassword());
        assertEquals(result.getRole(), author.getRole());
        assertEquals(result.getFirstName(), author.getFirstName());
        assertEquals(result.getLastName(), author.getLastName());
    }
}