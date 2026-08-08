package com.gridweaver.config;

import com.gridweaver.Security.JwtFilter;
import com.gridweaver.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter,
                          CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(Customizer.withDefaults())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))

                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(auth -> auth

                        // Public login/auth APIs
                        .requestMatchers("/auth/**").permitAll()

                        // Allow WebSocket handshake
                        .requestMatchers("/battery-websocket/**").permitAll()

                        // Everyone can view batteries
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/battery/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "OPERATOR",
                                "VIEWER"
                        )

                        // Admin & Operator can add batteries
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/battery/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "OPERATOR"
                        )

                        // Admin & Operator can update batteries
                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT,
                                "/battery/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "OPERATOR"
                        )

                        // Admin & Operator can delete batteries
                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE,
                                "/battery/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "OPERATOR"
                        )

                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }
}