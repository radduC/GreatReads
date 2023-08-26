package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.entities.users.Administrator;
import com.goodreads.demo.entities.users.User;
import com.goodreads.demo.repositories.AdministratorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorCreationService implements UserCreationService {

    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;

    @Override
    @Transactional
    public User createUser(RegisterRequest registerRequest) {
        Administrator administrator = new Administrator();

        administrator.setEmail(registerRequest.email());
        administrator.setPassword(passwordEncoder.encode(registerRequest.email()));
        administrator.setFirstName(registerRequest.firstName());
        administrator.setLastName(registerRequest.lastName());

        return administratorRepository.save(administrator);
    }
}
