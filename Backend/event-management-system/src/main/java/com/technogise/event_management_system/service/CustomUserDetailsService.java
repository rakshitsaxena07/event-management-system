package com.technogise.event_management_system.service;

import java.util.Collections;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.technogise.event_management_system.model.User;
import com.technogise.event_management_system.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList());  //user can only login and no  special roles/authorities for now
    }

}

//for understanding the flow of authentication in spring security
/*
Login Request
     ↓
Spring Security
     ↓
CustomUserDetailsService
     ↓
UserRepository.findByEmail()
     ↓
User found
     ↓
Convert to UserDetails
     ↓
Password comparison 
*/