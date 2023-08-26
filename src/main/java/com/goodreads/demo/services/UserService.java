package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreationServiceFactory userCreationServiceFactory;

    public UserDTO createUser(RegisterRequest registerRequest) {
        UserCreationService creationService = userCreationServiceFactory.getServiceForRole(registerRequest.role());
        var user = creationService.createUser(registerRequest);
        return new UserDTO(user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
