package com.goodreads.demo.services;

import com.goodreads.demo.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserCreationServiceFactory {

    private final Map<Role, UserCreationService> serviceMap;

    @Autowired
    public UserCreationServiceFactory(AdministratorCreationService administratorService,
                                      AuthorCreationService authorService,
                                      ReaderCreationService readerService) {

        serviceMap = Map.of(
            Role.ADMINISTRATOR, administratorService,
            Role.AUTHOR, authorService,
            Role.READER, readerService
        );
    }

    public UserCreationService getServiceForRole(Role role) {
        return serviceMap.get(role);
    }
}
