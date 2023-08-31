package com.goodreads.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @NotBlank(message = "An email is required")
        @Email(message = "invalid email", flags = Pattern.Flag.CASE_INSENSITIVE)
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
