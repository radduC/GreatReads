package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.entities.users.Author;
import com.goodreads.demo.entities.users.User;
import com.goodreads.demo.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorCreationService implements UserCreationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public User createUser(RegisterRequest registerRequest) {
        Author author = new Author();

        author.setEmail(registerRequest.email());
        author.setPassword(passwordEncoder.encode(registerRequest.email()));
        author.setFirstName(registerRequest.firstName());
        author.setLastName(registerRequest.lastName());

        return authorRepository.save(author);
    }
}
