package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.entities.users.Reader;
import com.goodreads.demo.entities.users.User;
import com.goodreads.demo.repositories.ReaderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderCreationService implements UserCreationService {

    private final PasswordEncoder passwordEncoder;
    private final ReaderRepository readerRepository;

    @Override
    @Transactional
    public User createUser(RegisterRequest registerRequest) {
        Reader reader = new Reader();

        reader.setEmail(registerRequest.email());
        reader.setPassword(passwordEncoder.encode(registerRequest.password()));
        reader.setFirstName(registerRequest.firstName());
        reader.setLastName(registerRequest.lastName());

        return readerRepository.save(reader);
    }
}
