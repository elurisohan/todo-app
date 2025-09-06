package com.tracknote.service;

import com.tracknote.Jwtutil;
import com.tracknote.dao.UserRepository;
import com.tracknote.dto.AuthRequest;
import com.tracknote.dto.AuthResponse;
import com.tracknote.dto.RegisterRequest;
import com.tracknote.exception.InvalidCredentials;
import com.tracknote.exception.UserNotFoundException;
import com.tracknote.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Jwtutil jwtUtil;

    public AuthResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists");
        } else if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User newUser=User.builder().username(request.getUsername()).fullname(request.getName()).email(request.getEmail()).password(request.getPassword()).build();

        System.out.println("Saving user: " + newUser);
        userRepository.save(newUser);
        System.out.println("Saved user successfully");
        return new AuthResponse(jwtUtil.generateToken(newUser.getUsername()));
    }

    public AuthResponse login(AuthRequest request){
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UserNotFoundException(request.getEmail()));
        if (!user.getPassword().equals(request.getPassword())){
            throw new InvalidCredentials();
        }
        return new AuthResponse(jwtUtil.generateToken(user.getUsername()));
    }





}
