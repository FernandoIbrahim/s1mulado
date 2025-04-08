package com.example.S1mulado.domain.auth.dto;

import com.example.S1mulado.domain.user.Role;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotNull(message = "The username is required")
        String username,

        @NotNull(message = "The email is required")
        String email,

        @NotNull(message = "The phone number is required")
        String phoneNumber,

        @NotNull(message = "The password is required")
        String password ) {
}
