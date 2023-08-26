package com.goodreads.demo.security;

import com.goodreads.demo.repositories.AdministratorRepository;
import com.goodreads.demo.repositories.AuthorRepository;
import com.goodreads.demo.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;
    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        var administrator = administratorRepository.findByEmail(email);

        if (administrator.isPresent()) {
            return new SecurityUserDetails(administrator.get());
        }

        var author = authorRepository.findByEmail(email);

        if (author.isPresent()) {
            return new SecurityUserDetails(author.get());
        }

        var reader = readerRepository.findByEmail(email);

        if (reader.isPresent()) {
            return new SecurityUserDetails(reader.get());
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
