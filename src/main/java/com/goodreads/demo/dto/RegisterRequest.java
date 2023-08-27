package com.goodreads.demo.dto;

import com.goodreads.demo.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record RegisterRequest(
    @NotBlank(message = "An email is required")
    @Email(message = "Invalid email", flags = Pattern.Flag.CASE_INSENSITIVE)
    String email,

    @NotBlank(message = "A password is required")
    String password,

    @NotNull(message = "A role is required")
    Role role,

    String firstName,
    String lastName
) {
}
