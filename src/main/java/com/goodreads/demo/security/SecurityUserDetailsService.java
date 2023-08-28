package com.goodreads.demo.security;

import com.goodreads.demo.dto.UserDTO;
import com.goodreads.demo.entities.User;
import com.goodreads.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            UserDTO userDTO = new UserDTO(user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getRole(), user.isAccountEnabled(),
                user.isAccountNonLocked(), user.isAccountNonExpired(), user.isCredentialsNonExpired());
            return new SecurityUserDetails(userDTO);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
