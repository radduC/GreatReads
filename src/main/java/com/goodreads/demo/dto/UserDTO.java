package com.goodreads.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goodreads.demo.enums.Role;

public record UserDTO(
    @JsonIgnore
    String password,
    String email,
    String firstName,
    String lastName,
    Role role
) {
}
