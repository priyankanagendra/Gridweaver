package com.gridweaver.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        switch (username) {

            case "admin":
                return User.withUsername("admin")
                        .password("admin123")
                        .roles("ADMIN")
                        .build();

            case "operator":
                return User.withUsername("operator")
                        .password("operator123")
                        .roles("OPERATOR")
                        .build();

            case "viewer":
                return User.withUsername("viewer")
                        .password("viewer123")
                        .roles("VIEWER")
                        .build();

            default:
                throw new UsernameNotFoundException("User not found");
        }
    }
}