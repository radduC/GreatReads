package com.goodreads.demo.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record PasswordChangeRequest(
    @NotBlank(message = "A password is required")
    String oldPassword,

    @NotBlank(message = "A password is required")
    String newPassword
) {
    @AssertTrue(message = "New password must be different from old password")
    public boolean isPasswordDifferent() {
        return !newPassword.equals(oldPassword);
    }
}
