package com.tracknote.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {


    @NotBlank(message = "Please enter your name")
    private String name;

    @NotBlank(message = "Please enter your username")
    private String username;

    @Email(message = "Please enter your email address")
    private String email;

    @NotBlank(message = "Please follow the password criteria")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!]).{8,}$",message = "Password must be at least 8 characters and include an uppercase letter, a number, and a special character")
    private String password;

}
