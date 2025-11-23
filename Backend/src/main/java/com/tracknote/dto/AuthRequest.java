package com.tracknote.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Please enter you email address")
    private String email;

    @NotBlank(message ="Please enter your password" )
    private String password;

}
