package com.gridweaver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gridweaver.dto.UserDTO;
import com.gridweaver.service.UserService;

import jakarta.validation.Valid;
import com.gridweaver.dto.LoginRequest;
import com.gridweaver.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(
            @Valid @RequestBody UserDTO userDTO) {

        UserDTO savedUser = userService.registerUser(userDTO);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest loginRequest) {

        LoginResponse response = userService.loginUser(loginRequest);

        return ResponseEntity.ok(response);
    }
}