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

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
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
