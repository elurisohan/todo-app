package com.tracknote.controller;

import com.tracknote.dto.AuthRequest;
import com.tracknote.dto.AuthResponse;
import com.tracknote.dto.RegisterRequest;
import com.tracknote.exception.InvalidCredentials;
import com.tracknote.service.ProjectService;
import com.tracknote.service.TaskService;
import com.tracknote.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController//Controller + ResponseBody
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor//It takes the final variables and creates a constructor. If you don't use @RequiredArgsConstructor (or manually define a constructor) in your class but have final fields, Java will not generate a default (no-arg) constructor for you. This is because: Final fields must be initialized exactly once, either at their declaration or inside every constructor. If you don't initialize final fields where declared, and don't define a constructor that initializes them, the code will not compile. Java only provides a default no-arg constructor if you don't define any constructor and if there are no final fields that require initialization.
public class AuthController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequest authRequest) {
        try {
            AuthResponse response = userService.login(authRequest);
            return ResponseEntity.ok(response);
        }
        catch (InvalidCredentials e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
