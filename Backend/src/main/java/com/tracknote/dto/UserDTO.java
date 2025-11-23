package com.tracknote.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {


    private String username;

    private String fullname;

    private String email;

    private String password;}
