package com.urbanclone.urbanclonebackend.service;

import com.urbanclone.urbanclonebackend.dto.AuthResponse;
import com.urbanclone.urbanclonebackend.dto.LoginRequest;
import com.urbanclone.urbanclonebackend.dto.RegisterRequest;
import com.urbanclone.urbanclonebackend.entity.User;
import com.urbanclone.urbanclonebackend.enums.Role;
import com.urbanclone.urbanclonebackend.repository.UserRepository;
import com.urbanclone.urbanclonebackend.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user.setRole(Role.USER);

        userRepository.save(user);

        String token =
                jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}