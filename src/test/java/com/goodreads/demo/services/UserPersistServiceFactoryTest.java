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
class UserPersistServiceFactoryTest {

    @Autowired
    private UserPersistServiceFactory userPersistServiceFactory;

    @MockBean
    private AdministratorPersistService administratorService;

    @MockBean
    private AuthorPersistService authorService;

    @MockBean
    private ReaderPersistService readerService;

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
        UserPersistService userPersistService1 = userPersistServiceFactory.getServiceForRole(role1);
        UserPersistService userPersistService2 = userPersistServiceFactory.getServiceForRole(role2);
        UserPersistService userPersistService3 = userPersistServiceFactory.getServiceForRole(role3);

        assertTrue(userPersistService1 instanceof AdministratorPersistService);
        assertTrue(userPersistService2 instanceof AuthorPersistService);
        assertTrue(userPersistService3 instanceof ReaderPersistService);
    }
}