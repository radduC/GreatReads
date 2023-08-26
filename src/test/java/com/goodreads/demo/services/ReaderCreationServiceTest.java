package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.entities.users.Reader;
import com.goodreads.demo.enums.Role;
import com.goodreads.demo.repositories.ReaderRepository;
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
class ReaderCreationServiceTest {

    @Autowired
    private ReaderCreationService readerCreationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private ReaderRepository readerRepository;

    RegisterRequest validRegisterRequest;
    Reader reader;

    @BeforeEach
    void setUp() {
        validRegisterRequest = new RegisterRequest("email@domain.com", "123", Role.ADMINISTRATOR, "firstName", "lastName");
        reader = new Reader(1, validRegisterRequest.email(), passwordEncoder.encode(validRegisterRequest.password()),
            validRegisterRequest.firstName(), validRegisterRequest.lastName());
    }

    @Test
    void createValidUserTest() {
        when(readerRepository.save(any(Reader.class))).thenReturn(reader);
        var result = readerCreationService.createUser(validRegisterRequest);
        verify(readerRepository).save(any(Reader.class));

        assertNotNull(result);
        assertTrue(passwordEncoder.matches(validRegisterRequest.password(), result.getPassword()));
        assertEquals(result.getId(), reader.getId());
        assertEquals(result.getEmail(), reader.getEmail());
        assertEquals(result.getPassword(), reader.getPassword());
        assertEquals(result.getRole(), reader.getRole());
        assertEquals(result.getFirstName(), reader.getFirstName());
        assertEquals(result.getLastName(), reader.getLastName());
    }
}