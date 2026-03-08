package com.technogise.event_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technogise.event_management_system.dto.LoginRequest;
import com.technogise.event_management_system.dto.RegisterRequest;
import com.technogise.event_management_system.model.User;
import com.technogise.event_management_system.security.JwtService;
import com.technogise.event_management_system.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterRequest request) {

    User user = authService.register(request);

    return ResponseEntity.ok(user);
  }

  // LOGIN controller
  @Autowired
  private AuthenticationManager
      authenticationManager; // Spring Security's main interface for authenticating users

  @Autowired private JwtService jwtService;

  @PostMapping("/login")
  public String login(@RequestBody LoginRequest request) {

    Authentication authentication =
        authenticationManager
            .authenticate( // This will use our CustomUserDetailsService to load the user by email
                // and compare the provided password with the stored hashed password.
                new UsernamePasswordAuthenticationToken( // creates token based on the email and
                    // password provided in the login request
                    request.getEmail(), request.getPassword()));

    return jwtService.generateToken(request.getEmail());
  }
}
