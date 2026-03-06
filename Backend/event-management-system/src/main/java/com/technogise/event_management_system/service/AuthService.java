package com.technogise.event_management_system.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.technogise.event_management_system.dto.RegisterRequest;
import com.technogise.event_management_system.enums.UserRole;
import com.technogise.event_management_system.model.User;
import com.technogise.event_management_system.repository.UserRepository;
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
    }
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(UserRole.ATTENDEE);

       
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        return userRepository.save(user);
    }
}
