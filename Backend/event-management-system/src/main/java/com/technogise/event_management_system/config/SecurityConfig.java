package com.technogise.event_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {        //PasswordEncoder=>This is the return type-> an interface from Spring Security
        return new BCryptPasswordEncoder();            // password hashing function
    }
}
