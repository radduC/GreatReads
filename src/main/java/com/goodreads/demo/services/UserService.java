package com.goodreads.demo.services;

import com.goodreads.demo.dto.RegisterRequest;
import com.goodreads.demo.dto.UserDTO;
import com.goodreads.demo.entities.User;
import com.goodreads.demo.exceptions.IdenticalEmailException;
import com.goodreads.demo.exceptions.UserAlreadyExistsException;
import com.goodreads.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(RegisterRequest registerRequest) {
        String email = registerRequest.email().toLowerCase();
        var existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + registerRequest.email());
        }

        User user = User.builder()
            .email(email)
            .password(passwordEncoder.encode(registerRequest.password()))
            .firstName(registerRequest.firstName())
            .lastName(registerRequest.lastName())
            .role(registerRequest.role())
            .accountNonLocked(true)
            .accountEnabled(true)
            .accountNonExpired(true)
            .credentialsNonExpired(true)
            .build();

        var result = userRepository.save(user);

        return UserDTO.builder()
            .email(result.getEmail())
            .firstName(result.getFirstName())
            .lastName(result.getLastName())
            .role(result.getRole())
            .accountNonLocked(result.isAccountNonLocked())
            .accountEnabled(result.isAccountEnabled())
            .accountNonExpired(result.isAccountNonExpired())
            .credentialsNonExpired(result.isCredentialsNonExpired())
            .build();
    }

    @Transactional
    public void setAccountNonLockValue(String email, boolean lockValue) {
        var user = userRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException("User not found with email: " + email));

        user .setAccountNonLocked(lockValue);
    }

    @Transactional
    public void changeEmail(String currentEmail, String newEmail) {
        newEmail = newEmail.toLowerCase();

        if (currentEmail.equalsIgnoreCase(newEmail)) {
            throw new IdenticalEmailException("The new email is identical to the old one!");
        }

        var checkNewEmail = userRepository.findByEmail(newEmail);

        if (checkNewEmail.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + newEmail);
        }

        var user = userRepository.findByEmail(currentEmail).orElseThrow(
            () -> new UsernameNotFoundException("User not found with email: " + currentEmail));

        user.setEmail(newEmail);
    }
}
