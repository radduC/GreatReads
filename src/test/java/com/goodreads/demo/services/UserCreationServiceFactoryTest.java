package com.goodreads.demo.services;

import com.goodreads.demo.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@SpringJUnitConfig
class UserCreationServiceFactoryTest {

    @Autowired
    private UserCreationServiceFactory userCreationServiceFactory;

    @MockBean
    private AdministratorCreationService administratorService;

    @MockBean
    private AuthorCreationService authorService;

    @MockBean
    private ReaderCreationService readerService;

    Role role1;
    Role role2;
    Role role3;

    @BeforeEach
    void setUp() {
        role1 = Role.ADMINISTRATOR;
        role2 = Role.AUTHOR;
        role3 = Role.READER;
    }

    @Test
    void getServiceForRoleTest() {
        UserCreationService userCreationService1 = userCreationServiceFactory.getServiceForRole(role1);
        UserCreationService userCreationService2 = userCreationServiceFactory.getServiceForRole(role2);
        UserCreationService userCreationService3 = userCreationServiceFactory.getServiceForRole(role3);

        assertTrue(userCreationService1 instanceof AdministratorCreationService);
        assertTrue(userCreationService2 instanceof AuthorCreationService);
        assertTrue(userCreationService3 instanceof ReaderCreationService);
    }
}