package com.goodreads.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record UserDTO(
    @JsonIgnore
    String password,
    String email,
    String firstName,
    String lastName
) {
}
