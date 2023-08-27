package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringJUnitConfig
class AdministratorCreationServiceTest {

    @Autowired
    private AdministratorPersistService administratorCreationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AdministratorRepository administratorRepository;

    RegisterRequest validRegisterRequest;
    Administrator administrator;

    @BeforeEach
    void setUp() {
        validRegisterRequest = new RegisterRequest("email@domain.com", "123", Role.ADMINISTRATOR, "firstName", "lastName");
        administrator = new Administrator(1, validRegisterRequest.email(), passwordEncoder.encode(validRegisterRequest.password()),
            validRegisterRequest.firstName(), validRegisterRequest.lastName());
    }

    @Test
    void createValidUserTest() {
        when(administratorRepository.save(any(Administrator.class))).thenReturn(administrator);
        var result = administratorCreationService.createUser(validRegisterRequest);
        verify(administratorRepository).save(any(Administrator.class));

        assertNotNull(result);
        assertTrue(passwordEncoder.matches(validRegisterRequest.password(), result.getPassword()));
        assertEquals(result.getId(), administrator.getId());
        assertEquals(result.getEmail(), administrator.getEmail());
        assertEquals(result.getPassword(), administrator.getPassword());
        assertEquals(result.getRole(), administrator.getRole());
        assertEquals(result.getFirstName(), administrator.getFirstName());
        assertEquals(result.getLastName(), administrator.getLastName());
    }
}