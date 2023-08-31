package com.goodreads.demo.security;

import com.goodreads.demo.dto.UserDTO;
import com.goodreads.demo.entities.User;
import com.goodreads.demo.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

public record SecurityUserDetails(UserDTO user, UserRepository userRepository) implements UserDetails {

    public User getUserEntity() {
        return userRepository.findByEmail(user.email()).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public String getUsername() {
        return user.email();
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.role()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.accountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user().accountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.credentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.accountEnabled();
    }
}
