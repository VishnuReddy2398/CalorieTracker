package com.vishnutechverse.calorietracker.controller;

import com.vishnutechverse.calorietracker.dto.AuthRequest;
import com.vishnutechverse.calorietracker.dto.AuthResponse;
import com.vishnutechverse.calorietracker.entity.User;
import com.vishnutechverse.calorietracker.repository.UserRepository;
import com.vishnutechverse.calorietracker.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid  AuthRequest request) {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());

        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
