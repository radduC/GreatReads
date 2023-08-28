package com.goodreads.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goodreads.demo.enums.Role;
import lombok.Builder;

@Builder
public record UserDTO(
    @JsonIgnore
    String password,
    String email,
    String firstName,
    String lastName,
    Role role,
    Boolean accountEnabled,
    Boolean accountNonLocked,
    Boolean accountNonExpired,
    Boolean credentialsNonExpired
) {
}
