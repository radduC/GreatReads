package com.goodreads.demo.services;

import com.goodreads.demo.dto.LoginRequest;
import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserCreationServiceFactory userCreationServiceFactory;
    private final UserDetailsService userDetailsService;

    public UserDTO createUser(RegisterRequest registerRequest) {
        UserCreationService creationService = userCreationServiceFactory.getServiceForRole(registerRequest.role());
        var user = creationService.createUser(registerRequest);
        return new UserDTO(user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole());
    }

    public void login(LoginRequest loginRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.email());
    }
}
